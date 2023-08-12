package br.unesp.frotaveiculos.adapters.db.ports;

import br.unesp.frotaveiculos.dto.FuncionarioDTORequest;
import br.unesp.frotaveiculos.dto.FuncionarioDTOResponse;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioPersist {
    public FuncionarioDTORequest cadastrarFuncionario(FuncionarioDTORequest funcionarioDTORequest);
    public Boolean isFuncionarioCadastrado(FuncionarioDTORequest funcionarioDTORequest);
    Page<FuncionarioDTOResponse> listarFuncionariosPaginado(Pageable pageable);
    FuncionarioDTORequest buscarPorId(Long id);
    FuncionarioDTORequest atualizar(Long id, FuncionarioUpdateDTO updateDTO);
    void deletar(Long id);
}
