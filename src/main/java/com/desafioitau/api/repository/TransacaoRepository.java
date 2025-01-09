package com.desafioitau.api.repository;

import com.desafioitau.api.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoRepository {

    private final List<Transacao> transacoes;

    public TransacaoRepository() {
        this.transacoes = new ArrayList<>();
    }

    public void add(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return this.transacoes;
    }

    public void deletaTransacoes() {
        transacoes.clear();
    }
}
