package br.unesp.frotaveiculos.adapters.db.repository;

import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}
