package com.desafio.votacao.service.implementacoes;

import com.desafio.votacao.dtos.pauta.PautaRequestDto;
import com.desafio.votacao.dtos.pauta.PautaResponseDto;
import com.desafio.votacao.exceptions.pauta.PautaExistenteException;
import com.desafio.votacao.exceptions.pauta.PautaInexistenteException;
import com.desafio.votacao.mappers.PautaMapper;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.models.Voto;
import com.desafio.votacao.repository.PautaRepository;
import com.desafio.votacao.service.PautaServiceI;
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
        Pauta p1 = mapper.toEntity(dto);
        Pauta pautaSalva = repository.save(p1);
        return mapper.toResponse(pautaSalva);
    }

    private void validarSePautaExiste(String tema){
        Optional<Pauta> pauta = repository.findByTema(tema.toUpperCase());
        if(pauta.isPresent()){
            throw new PautaExistenteException("A pauta de tema: "+ tema
                    + " já existe!");
        }
    }

    protected Pauta buscarPautaPorId(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new PautaInexistenteException("A pauta id: "+id+" não consta no banco.")
        );
    }

    protected void salvarVotoNaPauta(Pauta pauta, Voto voto){
        pauta.getVotos().add(voto);
        repository.save(pauta);
    }

}