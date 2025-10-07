package com.desafio.votacao.service.implementacoes;

import com.desafio.votacao.dtos.voto.VotoRequestDto;
import com.desafio.votacao.dtos.voto.VotoResponseDto;
import com.desafio.votacao.exceptions.voto.VotoJaProferidoException;
import com.desafio.votacao.mappers.VotoMapper;
import com.desafio.votacao.models.Pauta;
import com.desafio.votacao.models.Voto;
import com.desafio.votacao.repository.VotoRepository;
import com.desafio.votacao.service.VotoServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VotoServiceImpl implements VotoServiceI {
    private final VotoRepository repository;

    private final PautaServiceImpl pautaService;

    private final  SessaoServiceImpl sessaoService;
    private VotoMapper mapper = VotoMapper.INSTANCE;
    @Override
    public VotoResponseDto receberVoto(VotoRequestDto novoVoto) {
        Pauta pauta = pautaService.buscarPautaPorId(novoVoto.idDaPauta());
        sessaoService.validaPautaEmVotacao(pauta);
        validarVotoDoAssociado(novoVoto.cpfDoAssociado(),pauta);
        Voto voto = mapper.toEntity(novoVoto);
        voto.setPauta(pauta);
        Voto votoSalvo = repository.save(voto);
        pautaService.salvarVotoNaPauta(pauta,votoSalvo);
        return mapper.toResponse(votoSalvo);
    }


    private void validarVotoDoAssociado(String cpf, Pauta pauta){
        if (repository.existsByCpfAssociadoInPauta(cpf, pauta.getId())){
            throw new VotoJaProferidoException("O associado CPF: "+cpf+" já votou na pauta, tema: "
                    +pauta.getTema().toUpperCase());
        }
    }
}