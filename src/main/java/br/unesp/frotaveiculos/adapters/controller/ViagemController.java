package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import br.unesp.frotaveiculos.adapters.db.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    @PostMapping
    public ResponseEntity<Viagem> cadastrarTeste(Viagem viagem) {
        viagem = viagemRepository.save(viagem);
        return ResponseEntity.ok().body(viagem);
    }
}
