package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import br.unesp.frotaveiculos.dto.FuncionarioDTO;
import br.unesp.frotaveiculos.dto.FuncionarioDTOSemSenha;
import br.unesp.frotaveiculos.usecase.funcionario.FuncionarioUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioUC funcionarioUC;

    //Todo:  teste - Remover depois...
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO funcionarioCadastrado = funcionarioUC.cadastrarFuncionario(funcionarioDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTOSemSenha>> listaFuncionariosPaginado(Pageable pageable) {
        Page<FuncionarioDTOSemSenha> funcionariosDTOPage = funcionarioUC.listarComPaginacao(pageable);
        return ResponseEntity.ok(funcionariosDTOPage);
    }

    //Todo: teste - Remover depois...
    @GetMapping("/teste")
    public ResponseEntity<List<Funcionario>> listaFuncionariosPaginado() {
        List<Funcionario> entidade = funcionarioRepository.findAll();
        return ResponseEntity.ok(entidade);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPeloId(@PathVariable Long id) {
        FuncionarioDTO funcionarioDTO = funcionarioUC.buscarPorId(id);

        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> atualizaFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO updateDTO) {
        FuncionarioDTO funcionarioDTO = funcionarioUC.atualizar(id, updateDTO);

        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioUC.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
