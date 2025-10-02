package com.desafio.votacao.dtos.voto;

import com.desafio.votacao.enuns.VotoTipo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Objeto para receber um novo voto.")
public record VotoRequestDto(

        @Schema(description = "Tipo de voto do associado.", example = "SIM")
        @NotNull(message = "O voto do associado é campo de preenchimento obrigatório.")
        VotoTipo voto,

        @Schema(description = "CPF do associado (apenas dígitos).", example = "12345678909")
        @NotBlank(message = "O CPF do associado é campo de preenchimento obrigatório.")
        @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos.")
        String cpfDoAssociado,

        @Schema(description = "Id da pauta a ser votada.", example = "32")
        @NotNull(message = "O Id da pauta a ser votada é campo de preenchimento obrigatório.")
        Long idDaPauta
) {
}
