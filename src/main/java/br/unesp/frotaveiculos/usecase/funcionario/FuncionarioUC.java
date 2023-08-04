package br.unesp.frotaveiculos.usecase.funcionario;

import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioUC {

    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO);

    Page<FuncionarioDTO> listarComPaginacao(Pageable pageable);

    FuncionarioDTO buscarPorId(Long id);

    FuncionarioDTO atualizar(Long id, FuncionarioUpdateDTO updateDTO);
}
