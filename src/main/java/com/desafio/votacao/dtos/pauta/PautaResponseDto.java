package com.desafio.votacao.dtos.pauta;

import com.desafio.votacao.models.Voto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Objeto de saida representando uma pauta cadastrada no banco.")
public record PautaResponseDto(
        @Schema(description = "ID único do personagem", example = "10")
        Long id,

        @Schema(description = "Tema da pauta", example = "Inteligência artificial")
        String tema,

        @Schema(description = "Lista de votos exarados na pauta.")
        List<Voto>votos
) {
}
