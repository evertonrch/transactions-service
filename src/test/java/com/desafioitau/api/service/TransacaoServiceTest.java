package com.desafioitau.api.service;

import com.desafioitau.api.dto.EstatisticaResponse;
import com.desafioitau.api.dto.TransacaoRequest;
import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.repository.TransacaoRepositoryMock;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;
    @Mock
    private TransacaoRepositoryMock repositoryMock;

    @Test
    void testCriaTransacao() {
        BigDecimal valor = new BigDecimal("123.0");
        OffsetDateTime horaTransacao = OffsetDateTime.now();
        TransacaoRequest request = new TransacaoRequest(valor, horaTransacao);

        transacaoService.criaTransacao(request);

        verify(repositoryMock, times(1)).save(any(Transacao.class));
    }

    @Test
    void testListarTransacoes() {
        Transacao transacao1 = new Transacao(new BigDecimal("123.0"), OffsetDateTime.now());
        Transacao transacao2 = new Transacao(new BigDecimal("456.0"), OffsetDateTime.now());

        when(repositoryMock.getTransacoes()).thenReturn(Arrays.asList(transacao1, transacao2));

        List<Transacao> transacoes = transacaoService.listarTransacoes();

        assertNotNull(transacoes);
        assertEquals(2, transacoes.size());
        assertEquals(transacao1, transacoes.get(0));
        assertEquals(transacao2, transacoes.get(1));
    }

    @Test
    void testListarTransacoesVazio() {
        when(repositoryMock.getTransacoes()).thenReturn(Collections.emptyList());

        List<Transacao> transacoes = transacaoService.listarTransacoes();

        assertNotNull(transacoes);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void testDeletarTransacoes() {
        repositoryMock.deletaTransacoes();

        verify(repositoryMock, times(1)).deletaTransacoes();
    }

    @Test
    void testDeletarTransacoesComExcecoes() {
        String exceptionMessage = "Erro ao deletar transações";
        doThrow(new UnsupportedOperationException(exceptionMessage))
                .when(repositoryMock)
                .deletaTransacoes();

        UnsupportedOperationException ex = assertThrows(UnsupportedOperationException.class, () -> {
            repositoryMock.deletaTransacoes();
        });

        assertEquals(exceptionMessage, ex.getMessage());
        verify(repositoryMock, times(1)).deletaTransacoes();
    }

    @Test
    void testGetEstatisticaComTransacoesRecentes() {
        Transacao t1 = new Transacao(new BigDecimal("100.0"), OffsetDateTime.now().minusSeconds(30));
        Transacao t2 = new Transacao(new BigDecimal("200.0"), OffsetDateTime.now().minusSeconds(10));
        List<Transacao> transacoes = List.of(t1, t2);

        when(repositoryMock.getTransacoes()).thenReturn(transacoes);

        EstatisticaResponse response = transacaoService.getEstatistica();

        assertThat(response.getCount(), is(2L));
        assertThat(response.getSum(), is(300.0));
        assertThat(response.getAvg(), is(150.0));
        assertThat(response.getMax(), is(200.0));
        assertThat(response.getMin(), is(100.0));

        verify(repositoryMock, times(1)).getTransacoes();
    }

    @Test
    void testGetEstatisticaSemTransacoesRecentes() {
        Transacao t1 = new Transacao(new BigDecimal("50.0"), OffsetDateTime.now().minusSeconds(70));
        Transacao t2 = new Transacao(new BigDecimal("150.0"), OffsetDateTime.now().minusSeconds(120));
        List<Transacao> transacoes = List.of(t1, t2);

        when(repositoryMock.getTransacoes()).thenReturn(transacoes);

        EstatisticaResponse response = transacaoService.getEstatistica();

        assertThat(response.getCount(), is(0L));
        assertThat(response.getSum(), is(0.0));
        assertThat(response.getAvg(), is(0.0));
        assertThat(response.getMax(), is(0.0));
        assertThat(response.getMin(), is(0.0));

        verify(repositoryMock, times(1)).getTransacoes();
    }
}