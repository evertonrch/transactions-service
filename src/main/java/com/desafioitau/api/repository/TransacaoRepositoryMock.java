package com.desafioitau.api.repository;

import com.desafioitau.api.model.Transacao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TransacaoRepositoryMock {

    private final List<Transacao> transacoes = new ArrayList<>();

    public void save(Transacao transacao) {
        this.transacoes.add(transacao);
    }

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public void deletaTransacoes() {
        this.transacoes.clear();
    }
}
