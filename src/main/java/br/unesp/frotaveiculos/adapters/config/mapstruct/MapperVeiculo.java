package br.unesp.frotaveiculos.adapters.config.mapstruct;

import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.dto.VeiculoDTO;
import br.unesp.frotaveiculos.dto.VeiculoUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapperVeiculo {

    @Mapping(target = "id", ignore = true)
    Veiculo convertDtoEmEntidade(VeiculoDTO dto);

    VeiculoDTO convertEntidadeEmDto(Veiculo entidade);

    Veiculo convertUpdateDtoEmEntidade(@MappingTarget Veiculo entidade, VeiculoUpdateDTO updateDTO);
}
