package com.desafioitau.api.model;

import com.desafioitau.api.utils.ExceptionConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transacao {

    @NotNull(message = ExceptionConstants.VALOR_NOT_NULL)
    @PositiveOrZero(message = ExceptionConstants.VALOR_DEVE_SER_MAIOR_IGUAL_ZERO)
    private BigDecimal valor;

    @NotNull(message = ExceptionConstants.DATA_HORA_NOT_NULL)
    @Past(message = ExceptionConstants.DATA_HORA_PAST)
    private OffsetDateTime dataHora;

    public BigDecimal getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
