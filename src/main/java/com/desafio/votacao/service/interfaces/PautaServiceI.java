package com.desafio.votacao.service.interfaces;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;

public interface PautaServiceI {

    PautaResponseDto cadastrar(PautaRequestDto dto);
}
