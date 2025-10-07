package com.desafio.votacao.dtos.sessao_votacao;

import com.desafio.votacao.models.Pauta;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoDto{
    private Pauta pauta;
    private LocalDateTime inicioDaVotacao;
    private LocalDateTime fimDaVotacao;
    private Integer quantidadeDeSim;
    private Integer quantidadeDeNao;
}