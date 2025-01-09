package com.desafioitau.api.service;

import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void criaTransacao(Transacao transacao) {
        transacaoRepository.add(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.getTransacoes();
    }
}
