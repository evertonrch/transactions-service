package com.desafioitau.api.controller;

import com.desafioitau.api.dto.EstatisticaResponse;
import com.desafioitau.api.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final TransacaoService transacaoService;

    public EstatisticaController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<EstatisticaResponse> getEstatistica() {
        var estatistica = transacaoService.getEstatistica();

        return ResponseEntity.ok(estatistica);
    }
}
