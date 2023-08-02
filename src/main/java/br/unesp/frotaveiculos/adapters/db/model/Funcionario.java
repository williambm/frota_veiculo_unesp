package br.unesp.frotaveiculos.adapters.db.model;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.Unidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    //TODO: Pensar se vale a pena - acho que não
    /*Pensar se coloco o "campus" para poder retirar relatório por campus, posso justificar aqui que o piloto é da reitoria e depois podemos abrir para os campus controlando a frota das
    unidades*/
   // private Unidade unidadeLotacao;


}
