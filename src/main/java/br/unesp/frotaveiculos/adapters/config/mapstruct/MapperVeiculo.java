package br.unesp.frotaveiculos.adapters.config.mapstruct;

import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.dto.VeiculoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MapperVeiculo {

    @Mapping(target = "id", ignore = true)
    Veiculo convertDtoEmEntidade(VeiculoDTO dto);

    VeiculoDTO convertEntidadeEmDto(Veiculo entidade);

    Veiculo convertUpdateDtoEmEntidade(VeiculoDTO updateDTO);
}
