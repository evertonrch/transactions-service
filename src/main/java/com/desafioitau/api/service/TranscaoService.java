package com.desafioitau.api.service;

import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranscaoService {

    private final TransacaoRepository transacaoRepository;

    public TranscaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void criaTransacao(Transacao transacao) {
        transacaoRepository.add(transacao);
    }

    public List<Transacao> listaTransacoes() {
        return transacaoRepository.getTransacoes();
    }


}
