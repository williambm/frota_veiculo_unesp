package br.unesp.frotaveiculos.adapters.db.repository;

import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import br.unesp.frotaveiculos.dto.ViagensStatusCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    @Query("select v from Viagem v where v.solicitante.matricula = ?1")
    Page<Viagem> findBySolicitante_Matricula(Long matricula, Pageable pageable);

    //Para converter a entidade no DTO o JPA nos permite fazendo um new dentro do Select
    @Query("SELECT NEW br.unesp.frotaveiculos.dto.ViagensStatusCountDTO( v.status, COUNT(v) as quantidade) FROM Viagem v GROUP BY v.status")
    List<ViagensStatusCountDTO> countGroupByStatus();

}
