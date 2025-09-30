package com.desafio.votacao.dtos.pauta;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Objeto para criar um nova pauta.")
public record PautaRequestDto(
        @Schema(description = "Tema debatido na pauta.", example = "Inteligência artificial.")
        @NotBlank(message = "O tema debatido na pauta é campo de preenchimento obrigatório.")
        String tema
) {
}
