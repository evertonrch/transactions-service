package com.desafioitau.api.model;

public record Estatistica(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {}
