package br.unesp.frotaveiculos.adapters.config.mapstruct;

import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapperFuncionario {

    @Mapping(target = "perfilFuncionario", source = "perfil")
    Funcionario convertUpdateDtoEmEntidade(@MappingTarget Funcionario entidade, FuncionarioUpdateDTO updateDTO);

    @Mapping(target = "perfil", source = "perfilFuncionario")
    FuncionarioDTO convertEntidadeEmDTO(Funcionario entidade);
}
