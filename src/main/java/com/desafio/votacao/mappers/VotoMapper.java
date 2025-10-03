package com.desafio.votacao.mappers;

import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;
import com.desafio.votacao.models.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    @Mapping(target = "pauta", ignore = true)
    Voto toEntity(VotoRequestDto dto);

    VotoResponseDto toResponse(Voto dto);
}