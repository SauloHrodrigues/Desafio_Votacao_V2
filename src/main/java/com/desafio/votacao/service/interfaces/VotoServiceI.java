package com.desafio.votacao.service.interfaces;

import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;

public interface VotoServiceI {

    VotoResponseDto receberVoto(VotoRequestDto novoVoto);
}
