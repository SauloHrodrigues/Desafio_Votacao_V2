package com.desafio.votacao.dtos.voto;

import com.desafio.votacao.enuns.VotoTipo;
import com.desafio.votacao.models.Pauta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de saida representando o voto cadastrado no banco.")
public record VotoResponseDto(

        @Schema(description = "ID único do voto", example = "10")
        Long id,
        @Schema(description = "Voto atribuido.", example = "SIM")
        VotoTipo voto,
        @Schema(description = "CPF do associado (apenas dígitos).", example = "12345678909")
        String cpfDoAssociado,
        @Schema(description = "ID da pauta votada", example = "10")
        Pauta pauta
) {
}
