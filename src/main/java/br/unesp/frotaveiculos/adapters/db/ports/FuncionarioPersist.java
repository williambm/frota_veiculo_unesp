package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioDTOSemSenha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioPersist {
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO);
    public Boolean isFuncionarioCadastrado(FuncionarioDTO funcionarioDTO);
    Page<FuncionarioDTOSemSenha> listarFuncionariosPaginado(Pageable pageable);
    FuncionarioDTO buscarPorId(Long id);
    FuncionarioDTO atualizar(Long id, FuncionarioDTO updateDTO);
    void deletar(Long id);
}
