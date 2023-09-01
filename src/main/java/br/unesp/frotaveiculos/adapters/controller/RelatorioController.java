package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.ports.ViagemPersist;
import br.unesp.frotaveiculos.dto.ViagensStatusCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private ViagemPersist viagemPersist;

    @GetMapping("/estatistiscas/viagens-solicitacao")
    public ResponseEntity<List<ViagensStatusCountDTO>> dadosHistoricoDeSolicitacao(){
        List<ViagensStatusCountDTO> viagensStatusCountDTO = viagemPersist.buscaHistoricoDeSolicitacoesPorCategoria();
        return ResponseEntity.ok().body(viagensStatusCountDTO);
    }
}
