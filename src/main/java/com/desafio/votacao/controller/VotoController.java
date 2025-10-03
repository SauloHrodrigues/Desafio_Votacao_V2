package com.desafio.votacao.controller;

import com.desafio.votacao.controller.interfaces.VotoSwaggerDoc;
import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;
import com.desafio.votacao.service.VotoServiceI;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/voto")
public class VotoController implements VotoSwaggerDoc {

    private final VotoServiceI service;
    @Override
    @PostMapping
    public ResponseEntity<VotoResponseDto> receberVoto(@Valid @RequestBody VotoRequestDto novoVoto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.receberVoto(novoVoto));    }
}
