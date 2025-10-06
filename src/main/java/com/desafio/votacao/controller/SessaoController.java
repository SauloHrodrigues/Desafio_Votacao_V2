package com.desafio.votacao.controller;

import com.desafio.votacao.controller.interfaces.SessaoSwaggerDoc;
import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/abrir_sessao")
public class SessaoController implements SessaoSwaggerDoc {
    @Override
    public ResponseEntity<SessaoAbertaDto> abrirSessao(Long id, NovaSessaoDto novaSessao) {
        return null;
    }

    @Override
    public ResponseEntity<ResultadoVotacaoDto> resultadoVotacao(Long id) {
        return null;
    }
}
