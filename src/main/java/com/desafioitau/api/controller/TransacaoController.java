package com.desafioitau.api.controller;

import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criaTranscao(@RequestBody Transacao transacao) {
        transacaoService.criaTransacao(transacao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listaTransacoes() {
        List<Transacao> transacoes = transacaoService.listarTransacoes();

        return transacoes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(transacoes);
    }
}
