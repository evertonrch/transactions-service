package com.desafioitau.api.builder;

import com.desafioitau.api.dto.EstatisticaResponse;

public class EstatisticaResponseBuilder {

    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public EstatisticaResponseBuilder count(long count) {
        this.count = count;
        return this;
    }

    public EstatisticaResponseBuilder sum(double sum) {
        this.sum = sum;
        return this;
    }

    public EstatisticaResponseBuilder avg(double avg) {
        this.avg = avg;
        return this;
    }

    public EstatisticaResponseBuilder min(double min) {
        this.min = min;
        return this;
    }

    public EstatisticaResponseBuilder max(double max) {
        this.max = max;
        return this;
    }

    public EstatisticaResponse build() {
        return new EstatisticaResponse(count, sum, avg, min, max);
    }
}
