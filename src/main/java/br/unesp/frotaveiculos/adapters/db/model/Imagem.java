package br.unesp.frotaveiculos.adapters.db.model;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.CategoriaImg;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeArquivo;

    private byte[] imagemBinario;
    private Long tamanhoFoto;
    private CategoriaImg categoriaImg;
}
