package com.desafioitau.api.dto;

import com.desafioitau.api.builder.EstatisticaResponseBuilder;

import java.util.DoubleSummaryStatistics;

public class EstatisticaResponse {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public EstatisticaResponse() {
    }

    public EstatisticaResponse(long count, double sum, double avg, double min, double max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public static EstatisticaResponse fromEstatisticas(DoubleSummaryStatistics estatisticas) {
        return new EstatisticaResponseBuilder()
                .sum(estatisticas.getSum())
                .count(estatisticas.getCount())
                .avg(estatisticas.getAverage())
                .max(Double.isInfinite(estatisticas.getMax()) ? 0.0 : estatisticas.getMax())
                .min(Double.isInfinite(estatisticas.getMin()) ? 0.0 : estatisticas.getMin())
                .build();
    }

    public long getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public static EstatisticaResponseBuilder builder() {
        return new EstatisticaResponseBuilder();
    }
}
