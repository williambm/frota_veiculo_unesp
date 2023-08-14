package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.dto.VeiculoDTO;
import br.unesp.frotaveiculos.usecase.veiculo.VeiculoUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoUC veiculoUC;

    @PostMapping
    public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDTO) {
        VeiculoDTO veiculoSalvo = veiculoUC.cadastrar(veiculoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoDTO>> listarVeiculosPaginado(Pageable pageable) {
        Page<VeiculoDTO> veiculosDTO = veiculoUC.listarComPaginacao(pageable);
        return ResponseEntity.ok().body(veiculosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoDTO> buscarPeloId(@PathVariable Long id) {
        VeiculoDTO veiculoDTO = veiculoUC.buscarPorId(id);
        return ResponseEntity.ok().body(veiculoDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable Long id) {
        veiculoUC.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id, @RequestBody VeiculoDTO updateDTO) {
        VeiculoDTO veiculoDTO = veiculoUC.atualizar(id, updateDTO);

        return ResponseEntity.ok().body(veiculoDTO);
    }


}
