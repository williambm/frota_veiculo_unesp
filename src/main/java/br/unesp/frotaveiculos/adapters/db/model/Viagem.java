package br.unesp.frotaveiculos.adapters.db.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "viagem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    //Ver se jogo no @mtm
//    @ManyToOne
//    @JoinColumn(name = "funcionario_id")
//    private Funcionario funcionario;

    @Embedded
    private Endereco origem;
//    @Embedded
//    private Endereco destino;


    //Auditoria
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;
}
