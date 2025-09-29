package com.desafio.votacao.controller.interfaces;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
@Tag(name = "Pauta", description = "Endpoints para gestão para cadartrar uma nova pauta")
public interface PautaSwaggerDoc {

    @Operation(summary = "Cadastrar pauta")
    @ApiResponse(responseCode = "201", description = "Pauta cadastrada com sucesso.",
            content = @Content(schema = @Schema(implementation = PautaResponseDto.class)))
    ResponseEntity<PautaResponseDto> cadastrar(PautaRequestDto novaPauta);
}
