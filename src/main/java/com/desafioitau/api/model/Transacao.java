package com.desafioitau.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transacao {

    private BigDecimal valor;
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
