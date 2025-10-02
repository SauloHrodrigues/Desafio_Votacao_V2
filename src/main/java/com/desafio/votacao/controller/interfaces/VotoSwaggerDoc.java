package com.desafio.votacao.controller.interfaces;

import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Voto", description = "Endpoints para receber um voto")
public interface VotoSwaggerDoc {

    @Operation(summary = "Receber voto")
    @ApiResponse(responseCode = "201", description = "Receber um voto com sucesso.",
            content = @Content(schema = @Schema(implementation = VotoResponseDto.class)))
    ResponseEntity<VotoResponseDto> receberVoto(VotoRequestDto novoVoto);
}
