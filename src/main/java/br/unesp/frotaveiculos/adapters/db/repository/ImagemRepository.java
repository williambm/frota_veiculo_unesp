package br.unesp.frotaveiculos.adapters.db.repository;

import br.unesp.frotaveiculos.adapters.db.model.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
}
