package com.desafio.votacao.service;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import com.desafio.votacao.exceptions.pauta.PautaExistenteException;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.repository.PautaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceImplTest {

    @InjectMocks
    private PautaServiceImpl service;

    @Mock
    private PautaRepository repository;

    @Test
    @DisplayName("Cadastrar uma nova pauta com sucesso")
    void cadastrar() {
        PautaRequestDto dto = new PautaRequestDto("Informatica");
        Pauta pauta = new Pauta(1L,"INFORMATICA",new ArrayList<>());

        when(repository.findByTema("Informatica".toUpperCase())).thenReturn(Optional.empty());
        when(repository.save(any(Pauta.class))).thenReturn(pauta);

        PautaResponseDto responseDto = service.cadastrar(dto);

        assertNotNull(responseDto);
        assertEquals(pauta.getId(),responseDto.id());
        assertEquals(pauta.getTema().toUpperCase(),responseDto.tema());
    }

    @Test
    @DisplayName("Dede lançar excessão ao cadastrar uma nova pauta com tema já cadastrado.")
    void lancarExcessao() {
        PautaRequestDto dto = new PautaRequestDto("Informatica");
        Pauta pauta = new Pauta(1L,"INFORMATICA",new ArrayList<>());

        when(repository.findByTema("Informatica".toUpperCase())).thenReturn(Optional.of(pauta));

        PautaExistenteException exception = assertThrows(PautaExistenteException.class,
                ()-> { service.cadastrar(dto);
        });

        assertEquals("A pauta de tema: Informatica já existe!",exception.getMessage());
    }
}