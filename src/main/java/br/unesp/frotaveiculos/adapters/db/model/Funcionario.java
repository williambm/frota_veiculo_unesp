package br.unesp.frotaveiculos.adapters.db.model;

import br.unesp.frotaveiculos.adapters.db.model.enumerations.PerfilFuncionario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Funcionario implements UserDetails {

    @Id
    private Long matricula;
    private String nome;
    private String email;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String funcao;
    @Enumerated(EnumType.STRING)
    private PerfilFuncionario perfilFuncionario;

    //Se remover um registro pai removo o filho, pois não tem sentido ter foto do perfil sem o respectivo perfil de usuário.
    //Todo: verificar melhores práticas aqui, o sonnar está reclamando desta forma !
    @OneToOne(cascade = CascadeType.REMOVE)
    private Imagem imagemPerfil;

    //Colocar a senha no banco em hash
    private String senha;

    //Auditoria
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final String PASSAGEIRO = "ROLE_PASSAGEIRO";
        final String MOTORISTA = "ROLE_MOTORISTA";
        final String ADMIN = "ROLE_ADMIN";

        if (perfilFuncionario.equals(PerfilFuncionario.ADMIN)) {
            return List.of(
                    new SimpleGrantedAuthority(ADMIN),
                    new SimpleGrantedAuthority(MOTORISTA),
                    new SimpleGrantedAuthority(PASSAGEIRO)
            );
        } else if (perfilFuncionario.equals(PerfilFuncionario.MOTORISTA)) {
            return List.of(
                    new SimpleGrantedAuthority(MOTORISTA),
                    new SimpleGrantedAuthority(PASSAGEIRO)
            );
        } else {
            return List.of(new SimpleGrantedAuthority(PASSAGEIRO));
        }

    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
