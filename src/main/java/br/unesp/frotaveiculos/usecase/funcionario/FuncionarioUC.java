package br.unesp.frotaveiculos.usecase.funcionario;

import br.unesp.frotaveiculos.dto.FuncionarioDTORequest;
import br.unesp.frotaveiculos.dto.FuncionarioDTOResponse;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioUC {

    public FuncionarioDTORequest cadastrarFuncionario(FuncionarioDTORequest funcionarioDTORequest);

    Page<FuncionarioDTOResponse> listarComPaginacao(Pageable pageable);

    FuncionarioDTORequest buscarPorId(Long id);

    FuncionarioDTORequest atualizar(Long id, FuncionarioUpdateDTO updateDTO);

    void deletar(Long id);
}
