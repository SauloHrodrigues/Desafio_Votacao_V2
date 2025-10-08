package com.desafio.votacao.service;

import com.desafio.votacao.dtos.sessao_votacao.NovaSessaoDto;
import com.desafio.votacao.dtos.sessao_votacao.ResultadoVotacaoDto;
import com.desafio.votacao.dtos.sessao_votacao.SessaoAbertaResponseDto;
import com.desafio.votacao.enuns.VotoTipo;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.models.Voto;
import com.desafio.votacao.service.implementacoes.PautaServiceImpl;
import com.desafio.votacao.service.implementacoes.SessaoServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoServiceImplTest {


    private SessaoServiceImpl service;

    @Mock
    private PautaServiceImpl pautaService;

    private Pauta pauta;

    private NovaSessaoDto novaSessaoDto;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pauta = new Pauta(1L,"teste",new ArrayList<>());
        novaSessaoDto = new NovaSessaoDto(1);

        service= new SessaoServiceImpl(pautaService);

    }

    @Test
    @DisplayName("Deve abrir uma sessão com sucesso.")
    void abrirSessao() {

        when(pautaService.buscarPautaPorId(1L)).thenReturn(pauta);

        SessaoAbertaResponseDto resposta = service.abrirSessao(1L,novaSessaoDto);

        assertNotNull(resposta);
        assertEquals(1L, resposta.getId());
        assertEquals("teste", resposta.getTema());
        assertNotNull(resposta.getInicioDaSessao());
        assertNotNull(resposta.getFimDaSessao());
    }

    @Test
    void resultado() {

        Voto voto1 = new Voto(1L, VotoTipo.SIM,"11122233344",pauta);
        Voto voto2 = new Voto(2L, VotoTipo.NAO,"22233344455",pauta);
        Voto voto3 = new Voto(3L, VotoTipo.SIM,"33344455566",pauta);

        pauta.getVotos().add(voto1);
        pauta.getVotos().add(voto2);
        pauta.getVotos().add(voto3);

        when(pautaService.buscarPautaPorId(1L)).thenReturn(pauta);

        // Act
        ResultadoVotacaoDto resultado = service.resultado(1L);

        // Assert
        verify(pautaService, times(1)).buscarPautaPorId(1L);
        assertNotNull(resultado);
        assertEquals(3,resultado.getPauta().getVotos().size());
        assertEquals(1L, resultado.getPauta().getId());
        assertEquals(2, resultado.getQuantidadeDeSim());
        assertEquals(1, resultado.getQuantidadeDeNao());
    }
}