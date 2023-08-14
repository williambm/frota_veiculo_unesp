package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.db.model.Endereco;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.adapters.db.model.Veiculo;
import br.unesp.frotaveiculos.adapters.db.model.Viagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.StatusViagem;
import br.unesp.frotaveiculos.adapters.db.model.enumerations.Unidade;
import br.unesp.frotaveiculos.adapters.db.repository.ViagemRepository;
import br.unesp.frotaveiculos.dto.ViagemDTO;
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

    @GetMapping
    public ResponseEntity<Page<Viagem>> findAll(Pageable pageable) {
        //todo: viagem querys - focar na abertura e traer apenas do solicitante e se der tempo colocar a atribuição de motorista (esse cara tem que ver todas assim como o admin)
        //Ter dois endpoint abertos aqui  - 1 para quem solicitou e outro que esteja apenas como SOLICITADA para os motoristas se atribuirem e o admin ver tb
        Page<Viagem> viagens = viagemRepository.findAll(pageable); //No repositório pego uma lista do objeto viagem que vai vir totalmente preenchido
        Viagem entidade = viagens.stream().findFirst().orElseThrow();

        //TODO 2: MUdar esse DTO para que ele traga dados de response co mais informações como nome do requisitante motorista e etc ! Fazer com Builder esses caras (de forma conicional)
        //todo 3: Separar isso no cleanArch
        ViagemDTO viagemDTO = ViagemDTO.builder()
                .id(entidade.getId())
                .solicitanteId(entidade.getSolicitante().getMatricula())
                .motoristaId(entidade.getMotorista().getMatricula())
                .build();
        return ResponseEntity.ok().body(viagens);
    }

    @PostMapping
    public ResponseEntity<ViagemDTO> cadastrarTeste2(@RequestBody ViagemDTO viagemDTO) {

        viagemDTO = viagemUC.cadastrar(viagemDTO);
        return ResponseEntity.ok().body(viagemDTO);
    }


}
