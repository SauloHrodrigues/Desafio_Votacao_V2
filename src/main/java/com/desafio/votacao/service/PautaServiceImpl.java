package com.desafio.votacao.service;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import com.desafio.votacao.exceptions.pauta.PautaExistenteException;
import com.desafio.votacao.mappers.PautaMapper;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.repository.PautaRepository;
import com.desafio.votacao.service.interfaces.PautaServiceI;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PautaServiceImpl implements PautaServiceI {
    private final PautaRepository repository;
    private PautaMapper mapper= PautaMapper.INSTANCE;
    @Override
    public PautaResponseDto cadastrar(PautaRequestDto dto) {
        validarSePautaExiste(dto.tema());
        Pauta pautaSalva = repository.save(mapper.toEntity(dto));
        return mapper.toResponse(pautaSalva);
    }

    private void validarSePautaExiste(String tema){
        Optional<Pauta> pauta = repository.findByTema(tema.toUpperCase());
        if(pauta.isPresent()){
            throw new PautaExistenteException("A pauta de tema: "+ tema
                    + " já existe!");
        }
    }
}
