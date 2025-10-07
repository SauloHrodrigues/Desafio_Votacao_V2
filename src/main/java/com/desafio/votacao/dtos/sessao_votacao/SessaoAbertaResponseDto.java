package com.desafio.votacao.dtos.sessao_votacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessaoAbertaResponseDto {

    private Long id;
    private String tema;
    @JsonFormat(pattern = "yyyy/MM/dd: HH:mm:ss")
    private LocalDateTime inicioDaSessao;
    @JsonFormat(pattern = "yyyy/MM/dd: HH:mm:ss")
    private LocalDateTime fimDaSessao;
}
