package com.desafio.votacao.controller;

import com.desafio.votacao.controller.interfaces.PautaSwaggerDoc;
import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pauta")
public class PautaController implements PautaSwaggerDoc {

    @PostMapping
    public ResponseEntity<PautaResponseDto> cadastrar(PautaRequestDto novaPauta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar());
    }
}
