package com.desafioitau.api.dto;

import com.desafioitau.api.utils.ExceptionConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequest(
        @NotNull(message = ExceptionConstants.VALOR_NOT_NULL)
        @PositiveOrZero(message = ExceptionConstants.VALOR_DEVE_SER_MAIOR_IGUAL_ZERO)
        BigDecimal valor,

        @NotNull(message = ExceptionConstants.DATA_HORA_NOT_NULL)
        @Past(message = ExceptionConstants.DATA_HORA_PAST)
        OffsetDateTime dataHora
) {}
