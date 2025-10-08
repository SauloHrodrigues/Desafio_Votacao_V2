package com.desafio.votacao.service.implementacoes;

import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaResponseDto;
import com.desafio.votacao.enuns.VotoTipo;
import com.desafio.votacao.exceptions.sessao.SessaoInexistenteException;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.models.Voto;
import com.desafio.votacao.service.SessaoServiceI;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SessaoServiceImpl implements SessaoServiceI {

    private final PautaServiceImpl pautaService;
    private Pauta pautaEmVotacao = null;
    private Boolean sessaoAberta = false;
    private LocalDateTime inicioDaSessao;
    private LocalDateTime fimDaSessao;

    @Override
    public SessaoAbertaResponseDto abrirSessao(Long id, NovaSessaoDto dto) {
        Pauta pauta = pautaService.buscarPautaPorId(id);
        pautaEmVotacao = pauta;
        inicioDaSessao = LocalDateTime.now();
        fimDaSessao = calcularFimDaSessao(dto);
        sessaoAberta = true;
        return respostaAberturaDePauta(pauta);
    }

    @Override
    public ResultadoVotacaoDto resultado(Long id) {
        Pauta pautaBuscada = pautaService.buscarPautaPorId(id);
        int sim = 0;
        int nao = 0;

        for (Voto voto : pautaBuscada.getVotos()) {
            if (voto.getVoto().equals(VotoTipo.valueOf("SIM"))) {
                sim++;
            } else {
                nao++;
            }
        }

        return ResultadoVotacaoDto.builder()
                .pauta(pautaBuscada)
                .inicioDaVotacao(inicioDaSessao)
                .fimDaVotacao(fimDaSessao)
                .quantidadeDeNao(nao)
                .quantidadeDeSim(sim)
                .build();

    }

    private SessaoAbertaResponseDto respostaAberturaDePauta(Pauta pauta) {
        return SessaoAbertaResponseDto.builder()
                .id(pauta.getId())
                .tema(pauta.getTema())
                .inicioDaSessao(inicioDaSessao)
                .fimDaSessao(fimDaSessao)
                .build();
    }

    private LocalDateTime calcularFimDaSessao(NovaSessaoDto dto) {
        if (dto.tempo() != 0) {
            return inicioDaSessao.plusMinutes(dto.tempo());
        } else {
            return inicioDaSessao.plusMinutes(2);
        }
    }

    protected void validaPautaEmVotacao(Pauta pauta) {
        if (!sessaoAberta) {
            throw new SessaoInexistenteException("Não há sessão de votação aberta.");
        }

        if (!pautaEmVotacao.equals(pauta)) {
            throw new SessaoInexistenteException("A pauta: " + pauta.getTema().toUpperCase() +
                    " não esta em votação.");
        }
    }

    @Scheduled(fixedDelay = 5000)
    private void agendamento() {
        LocalDateTime agora = LocalDateTime.now();
        if (sessaoAberta) {
            if (agora.isAfter(fimDaSessao)) {
                sessaoAberta = false;
            }
        }
    }
}