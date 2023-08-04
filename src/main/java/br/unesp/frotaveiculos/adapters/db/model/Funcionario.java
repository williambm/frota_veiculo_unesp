package br.unesp.frotaveiculos.adapters.db.model;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario {

    @Id
    private Long matricula;
    private String nome;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
    @Enumerated(EnumType.STRING)
    private PerfilFuncionario perfilFuncionario;

    //Auditoria
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;

}
