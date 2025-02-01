package com.desafioitau.api.service;

import com.desafioitau.api.dto.EstatisticaResponse;
import com.desafioitau.api.dto.TransacaoRequest;
import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.repository.TransacaoRepositoryMock;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    private static final int LIMITE_SEGUNDOS = 60;
    private final TransacaoRepositoryMock repositoryMock;

    public TransacaoService(TransacaoRepositoryMock repositoryMock) {
        this.repositoryMock = repositoryMock;
    }

    public void criaTransacao(TransacaoRequest request) {
        Transacao transacao = new Transacao(request.valor(), request.dataHora());
        repositoryMock.save(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return repositoryMock.getTransacoes();
    }

    public void deletaTransacoes() {
        repositoryMock.deletaTransacoes();
    }

    public EstatisticaResponse getEstatistica() {
        var estatistica = repositoryMock.getTransacoes().stream()
                .filter(this::ocorreuNosUltimos60Segundos)
                .mapToDouble(transacao -> transacao.valor().doubleValue())
                .summaryStatistics();

        return EstatisticaResponse.fromEstatisticas(estatistica);
    }

    private boolean ocorreuNosUltimos60Segundos(Transacao transacao) {
        return Duration.between(transacao.dataHora(), OffsetDateTime.now()).getSeconds() <= LIMITE_SEGUNDOS;
    }
}
