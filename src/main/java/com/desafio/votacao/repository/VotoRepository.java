package com.desafio.votacao.repository;

import com.desafio.votacao.models.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto,Long> {

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
            "FROM Voto v " +
            "WHERE v.pauta.id = :pautaId AND v.cpfDoAssociado = :cpf")
    boolean existsByCpfAssociadoInPauta(@Param("cpf") String cpf, @Param("pautaId") Long pautaId);
}
