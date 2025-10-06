package com.desafio.votacao.controller.interfaces;

import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Sessão para abrir votação", description = "Endpoints para abrir votação e devolver resultado.")
public interface SessaoSwaggerDoc {

    @Operation(summary = "Abrir Sessão de votação")
    @ApiResponse(responseCode = "200", description = "Abrir a sessão de votação.",
            content = @Content(schema = @Schema(implementation = SessaoAbertaDto.class)))
    @ApiResponse(responseCode = "404", description = "Pauta não encontrado.")
    ResponseEntity<SessaoAbertaDto> abrirSessao(
            @Parameter(description = "ID da pauta.", example = "10")
            @PathVariable Long id,
            @Valid @RequestBody NovaSessaoDto novaSessao
    );

    @Operation(summary = "Resultado da votação.")
    @ApiResponse(responseCode = "200", description = "Retorna resultado da votação.",
            content = @Content(schema = @Schema(implementation = ResultadoVotacaoDto.class)))
    @ApiResponse(responseCode = "404", description = "Pauta não encontrado.")
    ResponseEntity<ResultadoVotacaoDto> resultadoVotacao(
            @Parameter(description = "Id da ", example = "1")
            @PathVariable Long id
    );
}