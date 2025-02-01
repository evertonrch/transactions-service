package com.desafioitau.api.controller;

import com.desafioitau.api.dto.TransacaoRequest;
import com.desafioitau.api.model.Transacao;
import com.desafioitau.api.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criaTransacao(@RequestBody @Valid TransacaoRequest request) {
        transacaoService.criaTransacao(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listaTransacoes() {
        List<Transacao> transacoes = transacaoService.listarTransacoes();

        return transacoes.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>()) :
                ResponseEntity.ok(transacoes);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTransacoes() {
        transacaoService.deletaTransacoes();

        return ResponseEntity.ok().build();
    }
}
