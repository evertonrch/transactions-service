package com.desafioitau.api.controller;

import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.service.TranscaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TranscaoService transcaoService;

    public TransacaoController(TranscaoService transcaoService) {
        this.transcaoService = transcaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criaTranscao(@RequestBody Transacao transacao) {
        transcaoService.criaTransacao(transacao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listaTransacoes() {
        List<Transacao> transacoes = transcaoService.listaTransacoes();

        return transacoes.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(transacoes);
    }
}
