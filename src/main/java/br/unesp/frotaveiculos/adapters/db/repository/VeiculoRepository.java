package br.unesp.frotaveiculos.adapters.db.repository;

import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
