package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.dto.FuncionarioDTORequest;
import br.unesp.frotaveiculos.dto.FuncionarioDTOResponse;
import br.unesp.frotaveiculos.dto.FuncionarioUpdateDTO;
import br.unesp.frotaveiculos.usecase.funcionario.FuncionarioUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioUC funcionarioUC;

    @PostMapping
    public ResponseEntity<FuncionarioDTORequest> cadastrarFuncionario(@RequestBody FuncionarioDTORequest funcionarioDTORequest) {
        FuncionarioDTORequest funcionarioCadastrado = funcionarioUC.cadastrarFuncionario(funcionarioDTORequest);


        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTOResponse>> listaFuncionariosPaginado(Pageable pageable) {
        Page<FuncionarioDTOResponse> funcionariosDTOPage = funcionarioUC.listarComPaginacao(pageable);
        return ResponseEntity.ok(funcionariosDTOPage);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTORequest> buscarPeloId(@PathVariable Long id) {
        FuncionarioDTORequest funcionarioDTORequest = funcionarioUC.buscarPorId(id);

        return ResponseEntity.ok().body(funcionarioDTORequest);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTORequest> atualizaFuncionario(@PathVariable Long id, @RequestBody FuncionarioUpdateDTO updateDTO) {
        FuncionarioDTORequest funcionarioDTORequest = funcionarioUC.atualizar(id, updateDTO);

        return ResponseEntity.ok().body(funcionarioDTORequest);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioUC.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
