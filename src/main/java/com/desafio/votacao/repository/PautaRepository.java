package com.desafio.votacao.repository;

import com.desafio.votacao.models.Pauta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta,Long> {
    Optional<Pauta> findByTema(String tema);
}
