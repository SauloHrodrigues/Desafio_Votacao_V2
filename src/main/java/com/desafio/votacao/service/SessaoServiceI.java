package com.desafio.votacao.service;

import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaResponseDto;

public interface SessaoServiceI {
    SessaoAbertaResponseDto abrirSessao(Long id, NovaSessaoDto dto);

    ResultadoVotacaoDto resultado(Long id);
}
