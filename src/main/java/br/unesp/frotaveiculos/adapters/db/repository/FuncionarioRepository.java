package br.unesp.frotaveiculos.adapters.db.repository;

import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    UserDetails findByEmail(String username);
}
