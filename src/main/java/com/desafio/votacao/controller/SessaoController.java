package com.desafio.votacao.controller;

import com.desafio.votacao.controller.interfaces.SessaoSwaggerDoc;
import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaResponseDto;
import com.desafio.votacao.service.SessaoServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sessao")
public class SessaoController implements SessaoSwaggerDoc {

    private final SessaoServiceI service;

    @PostMapping("/abrir/{id}")
    @Override
    public ResponseEntity<SessaoAbertaResponseDto> abrirSessao(@PathVariable Long id, NovaSessaoDto novaSessao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.abrirSessao(id,novaSessao));
    }

    @GetMapping("/resultado/{id}")
    @Override
    public ResponseEntity<ResultadoVotacaoDto> resultadoVotacao(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.resultado(id));
    }
}
