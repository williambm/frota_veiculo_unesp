package br.unesp.frotaveiculos.adapters.db.model;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.Fabricante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    @Enumerated(EnumType.STRING)
    private Fabricante fabricante;
    private String modelo;
    private Integer totalPassageiros;
    private Long quilometragem;
    private Integer anoFabricacao;
    private Boolean possuiCacamba;


}
