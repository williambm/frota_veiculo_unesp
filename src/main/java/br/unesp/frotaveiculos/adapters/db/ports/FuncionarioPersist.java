package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioPersist {
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO);
    public Boolean isFuncionarioCadastrado(FuncionarioDTO funcionarioDTO);
    Page<FuncionarioDTO> listarFuncionariosPaginado(Pageable pageable);
    FuncionarioDTO buscarPorId(Long id);
    FuncionarioDTO atualizar(Long id, FuncionarioUpdateDTO updateDTO);
}
