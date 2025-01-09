package com.desafioitau.api.service;

import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;
    @Mock
    private TransacaoRepository transacaoRepository;

    @Test
    void testCriaTransacao() {
        Transacao transacao = new Transacao();
        transacao.setValor(new BigDecimal("123.0"));
        transacao.setDataHora(OffsetDateTime.now());

        transacaoService.criaTransacao(transacao);

        verify(transacaoRepository, times(1)).add(transacao);
    }

    @Test
    void testListarTransacoes() {
        Transacao transacao1 = new Transacao();
        transacao1.setValor(new BigDecimal("123.0"));
        Transacao transacao2 = new Transacao();
        transacao2.setValor(new BigDecimal("456.0"));

        when(transacaoRepository.getTransacoes()).thenReturn(Arrays.asList(transacao1, transacao2));

        List<Transacao> transacoes = transacaoService.listarTransacoes();

        assertNotNull(transacoes);
        assertEquals(2, transacoes.size());
        assertEquals(transacao1, transacoes.get(0));
        assertEquals(transacao2, transacoes.get(1));
    }

    @Test
    void testListarTransacoesVazio() {
        when(transacaoRepository.getTransacoes()).thenReturn(Collections.emptyList());

        List<Transacao> transacoes = transacaoService.listarTransacoes();

        assertNotNull(transacoes);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void testDeletarTransacoes() {
        transacaoRepository.deletaTransacoes();

        verify(transacaoRepository, times(1)).deletaTransacoes();
    }

    @Test
    void testDeletarTransacoesComExcecoes() {
        String exceptionMessage = "Erro ao deletar transações";
        doThrow(new UnsupportedOperationException(exceptionMessage))
                .when(transacaoRepository)
                .deletaTransacoes();

        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> {
            transacaoRepository.deletaTransacoes();
        });

        assertEquals(exceptionMessage, ex.getMessage());
        verify(transacaoRepository, times(1)).deletaTransacoes();
    }
}