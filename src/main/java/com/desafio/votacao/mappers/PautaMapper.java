package com.desafio.votacao.mappers;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import com.desafio.votacao.models.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    @Mapping(target = "tema", expression = "java(dto.tema() != null ? dto.tema().toUpperCase() : null)")
    Pauta toEntity(PautaRequestDto dto);

    PautaResponseDto toResponse(Pauta pauta);
}
