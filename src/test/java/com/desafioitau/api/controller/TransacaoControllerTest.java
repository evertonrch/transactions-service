package com.desafioitau.api.controller;

import com.desafioitau.api.dto.TransacaoRequest;
import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.service.TransacaoService;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    private Validator validator;

    @BeforeEach
    void init() {
        try(ValidatorFactory factory = buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testCriaTransacaoComSucesso() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now());

        ResponseEntity<Void> responseEntity = transacaoController.criaTransacao(request);

        assertThat(responseEntity.getBody(), is(nullValue()));
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));

        verify(transacaoService, times(1)).criaTransacao(request);
    }

    @Test
    void testCriaTransacaoComCorpoNulo() {
        TransacaoRequest request = new TransacaoRequest(null, null);
        ;

        var violacoes = validator.validate(request);

        assertFalse(violacoes.isEmpty());

        verify(transacaoService, never()).criaTransacao(any());

    }

    @Test
    void testCriaTransacaoComValorNulo() {
        TransacaoRequest request = new TransacaoRequest(null, OffsetDateTime.now());

        var violacoes = validator.validate(request);

        assertFalse(violacoes.isEmpty());

        verify(transacaoService, never()).criaTransacao(any());
    }

    @Test
    void testCriaTransacaoComDataHoraNulo() {
        TransacaoRequest request = new TransacaoRequest(null, OffsetDateTime.now());

        var violacoes = validator.validate(request);

        assertFalse(violacoes.isEmpty());

        verify(transacaoService, never()).criaTransacao(any());
    }

    @Test
    void testCriaTransacaoComErroInterno() {
        TransacaoRequest request = new TransacaoRequest(BigDecimal.TEN, OffsetDateTime.now());
        String exceptionMessage = "Erro inesperado";

        doThrow(new RuntimeException(exceptionMessage))
                .when(transacaoService)
                .criaTransacao(request);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                transacaoController.criaTransacao(request)
        );

        assertThat(exception.getMessage(), is(exceptionMessage));
    }

    @Test
    void listaTransacoesComValores() {
        BigDecimal valorPrimeiraTransacao = new BigDecimal("100.0");
        BigDecimal valorSegundaTransacao = new BigDecimal("100.0");
        Transacao t1 = new Transacao(valorPrimeiraTransacao, OffsetDateTime.now());
        Transacao t2 = new Transacao(valorSegundaTransacao, OffsetDateTime.now());
        List<Transacao> transacaos = List.of(t1, t2);

        when(transacaoService.listarTransacoes()).thenReturn(transacaos);

        var transacoesResponse = transacaoController.listaTransacoes();

        assertThat(transacoesResponse.getBody(), notNullValue());
        assertThat(transacoesResponse.getStatusCode(), is(HttpStatus.OK));
        assertThat(transacaos.size(), is(transacoesResponse.getBody().size()));
        assertThat(valorPrimeiraTransacao.add(valorSegundaTransacao), is(new BigDecimal("200.0")));

        verify(transacaoService, times(1)).listarTransacoes();
    }

    @Test
    void testListaTransacoesComRetornoVazio() {
        doReturn(Collections.emptyList())
                .when(transacaoService)
                .listarTransacoes();

        var transacoesResponse = transacaoController.listaTransacoes();

        assertThat(transacoesResponse.getBody(), is(notNullValue()));
        assertThat(transacoesResponse.getStatusCode(), is(HttpStatus.NO_CONTENT));
        assertThat(transacoesResponse.getBody().size(), is(0));
    }

    @Test
    void testDeletaTransacoes() {
        ResponseEntity<Void> responseEntity = transacaoController.deletaTransacoes();

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        verify(transacaoService, times(1)).deletaTransacoes();
    }
}