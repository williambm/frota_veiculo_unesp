package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.repository.ViagemRepository;
import br.unesp.frotaveiculos.dto.MotoristaAtribuicaoDTO;
import br.unesp.frotaveiculos.dto.ViagemDTO;
import br.unesp.frotaveiculos.dto.ViagemMaisInfoDTO;
import br.unesp.frotaveiculos.usecase.viagem.ViagemUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemUC viagemUC;
    @Autowired
    private ViagemRepository viagemRepository;

    @GetMapping()
    public ResponseEntity<Page<ViagemMaisInfoDTO>> findAllPaginado(Pageable pageable) {
        Page<ViagemMaisInfoDTO> viagensPorFuncionarioDTO = viagemUC.listarViagens(pageable);
        return ResponseEntity.ok().body(viagensPorFuncionarioDTO);
    }

    @GetMapping("/funcionario/{matricula}")
    public ResponseEntity<Page<ViagemMaisInfoDTO>> findAllByFuncionarioSolicitantePaginado(@PathVariable Long matricula, Pageable pageable) {
        Page<ViagemMaisInfoDTO> viagensPorFuncionarioDTO = viagemUC.listarViagensPorFuncionario(matricula, pageable);
        return ResponseEntity.ok().body(viagensPorFuncionarioDTO);
    }

    @PostMapping
    public ResponseEntity<ViagemDTO> cadastrarTeste(@RequestBody ViagemDTO viagemDTO) {

        viagemDTO = viagemUC.cadastrar(viagemDTO);
        return ResponseEntity.ok().body(viagemDTO);
    }

    @PostMapping("/{id}/motorista")
    public ResponseEntity<Void> atribuirMotorista(@PathVariable Long id, @RequestBody MotoristaAtribuicaoDTO dto) {

        viagemUC.atribuirMotorista(id, dto);
        return ResponseEntity.ok().build();
    }


}
