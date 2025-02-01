package com.desafioitau.api.controller;

import com.desafioitau.api.dto.EstatisticaResponse;
import com.desafioitau.api.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class EstatisticaControllerTest {

    @InjectMocks
    private EstatisticaController estatisticaController;

    @Mock
    private TransacaoService transacaoService;

    @Test
    void testRetornaEstatisticaComSucesso() {
        EstatisticaResponse response = EstatisticaResponse.builder()
                .max(10)
                .avg(12.0)
                .build();

        when(transacaoService.getEstatistica()).thenReturn(response);

        var responseEntity = estatisticaController.getEstatistica();

        assertThat(responseEntity.getBody(), notNullValue());
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getMax(), is(response.getMax()));
        assertThat(responseEntity.getBody().getAvg(), is(response.getAvg()));
    }

    @Test
    void testRetornaErroComEstatisticaInvalida() {
        String exceptionMessage = "um erro";

        doThrow(new RuntimeException(exceptionMessage))
                .when(transacaoService)
                .getEstatistica();

        RuntimeException ex = assertThrows(RuntimeException.class, () -> estatisticaController.getEstatistica());

        assertThat(ex.getMessage(), is(exceptionMessage));
    }
}