package br.unesp.frotaveiculos.adapters.db.ports.impl;

import br.unesp.frotaveiculos.adapters.config.mapstruct.MapperVeiculo;
import br.unesp.frotaveiculos.adapters.db.exceptions.FabricanteInvalidoDBException;
import br.unesp.frotaveiculos.adapters.db.exceptions.PerfilInvalidoDBException;
import br.unesp.frotaveiculos.adapters.db.exceptions.VeiculoDBInexistenteException;
import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.adapters.db.ports.VeiculoPersist;
import br.unesp.frotaveiculos.adapters.db.repository.VeiculoRepository;
import br.unesp.frotaveiculos.dto.VeiculoDTO;
import br.unesp.frotaveiculos.dto.VeiculoUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

@Component
public class VeiculoPersistImpl implements VeiculoPersist {

    @Autowired
    private MapperVeiculo mapperVeiculo;

    @Autowired
    private VeiculoRepository repository;

    @Override
    public VeiculoDTO salvar(VeiculoDTO veiculoDTO) {
        try {
            Veiculo entidade = mapperVeiculo.convertDtoEmEntidade(veiculoDTO);
            entidade = repository.save(entidade);

            veiculoDTO = mapperVeiculo.convertEntidadeEmDto(entidade);
            return veiculoDTO;
        } catch (Exception erroGenerico) {
            throw erroGenerico;
        }
    }

    @Override
    public Page<VeiculoDTO> listarVeiculosPaginado(Pageable pageable) {
        Page<Veiculo> listaDeVeiculos = repository.findAll(pageable);
        return listaDeVeiculos.map(entidade -> mapperVeiculo.convertEntidadeEmDto(entidade));
    }

    @Override
    public VeiculoDTO buscarPorId(Long id) {
        Veiculo entidade = repository.findById(id).orElseThrow(VeiculoDBInexistenteException::new);
        return mapperVeiculo.convertEntidadeEmDto(entidade);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VeiculoDTO atualizar(Long id, VeiculoUpdateDTO updateDTO) {
        try {
            Veiculo entidade = repository.findById(id).orElseThrow();
            entidade = mapperVeiculo.convertUpdateDtoEmEntidade(entidade, updateDTO);
            entidade = repository.save(entidade);

            return mapperVeiculo.convertEntidadeEmDto(entidade);
        } catch (NoSuchElementException ex) {
            //TODO: Atualizar Veículo inexistente está ocasionando erro 500 - mapear
            throw ex;
        } catch (IllegalArgumentException ex) {
            throw new FabricanteInvalidoDBException(MessageFormat.format(
                    "O fabricante {0} não está cadastrado em nossas bases, o que invalida o processo de atualização deste registro.", updateDTO.getFabricante()
            ));
        }
    }
}
