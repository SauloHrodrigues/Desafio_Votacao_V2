package com.desafio.votacao.service;

import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;
import com.desafio.votacao.enuns.VotoTipo;
import com.desafio.votacao.exceptions.voto.VotoJaProferidoException;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.models.Voto;
import com.desafio.votacao.repository.VotoRepository;
import com.desafio.votacao.service.implementacoes.PautaServiceImpl;
import com.desafio.votacao.service.implementacoes.VotoServiceImpl;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceImplTest {

    @InjectMocks
    private VotoServiceImpl service;

    @Mock
    private VotoRepository repository;

    @Mock
    private PautaServiceImpl pautaService;


    @Test
    @DisplayName("Deve Receber um voto com sucesso.")
    void receberVoto() {
        VotoRequestDto dto = new VotoRequestDto(VotoTipo.SIM,"33344466688",1L);
        Pauta pauta = new Pauta(1L,"tema Teste",new ArrayList<>());
        Voto voto = new Voto(1L,VotoTipo.SIM,"33344466688",pauta);

        when(pautaService.buscarPautaPorId(1L)).thenReturn(pauta);
        when(repository.save(any(Voto.class))).thenReturn(voto);

        VotoResponseDto responseDto = service.receberVoto(dto);

        assertNotNull(responseDto.id());
        assertEquals(dto.voto(),responseDto.voto());
        assertEquals(dto.cpfDoAssociado(), responseDto.cpfDoAssociado());
        assertEquals(dto.idDaPauta(),responseDto.pauta().getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao Receber um voto já cadastrado.")
    void lancaEcecaoAoReceberVoto() {
        VotoRequestDto dto = new VotoRequestDto(VotoTipo.SIM,"33344466688",1L);
        Pauta pauta = new Pauta(1L,"tema Teste",new ArrayList<>());
        Voto voto = new Voto(1L,VotoTipo.SIM,"33344466688",pauta);

        when(pautaService.buscarPautaPorId(1L)).thenReturn(pauta);
        when(repository.existsByCpfAssociadoInPauta(dto.cpfDoAssociado(),1L)).thenReturn(true);

        VotoJaProferidoException exception = assertThrows(VotoJaProferidoException.class,
                ()-> service.receberVoto(dto));

        assertTrue(exception.getMessage().contains(dto.cpfDoAssociado()));
        assertTrue(exception.getMessage().contains(pauta.getTema().toUpperCase()));
        assertTrue(exception.getMessage().contains(" já votou na pauta, tema: "));
    }
}