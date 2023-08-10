package br.unesp.frotaveiculos.adapters.config.security;

import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenManagerService {

    @Value("${jwt.senha}")
    private String senhaToken;

    public String gerarToken(Funcionario usuario) {
        //Para criar o token usamos: https://github.com/auth0/java-jwt#getting-started
        return JWT.create()
                .withIssuer("unesp_sistema_veiculos")                   //Emissor
                .withSubject(usuario.getUsername())                     //A quem destiná-se - userName aqui é o nosso email que será usado como login
                .withClaim("matricula", usuario.getMatricula())  //Informações extras sobre o usuário
                .withClaim("email", usuario.getEmail())          //Informações extras sobre o usuário
                .withClaim("email", usuario.getEmail())          //Informações extras sobre o usuário
                .withClaim("roles", String.valueOf(usuario.getPerfilFuncionario()))          //Informações extras sobre o usuário
                .withExpiresAt(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.of("-03:00")))  //Quando o token expira
                .sign(Algorithm.HMAC256(senhaToken));   //Algoritimo da senha do token
    }

    public String getSubject(String token) {
        //Retorna o login para que seja verificado novamente o usuário
        return JWT.require(Algorithm.HMAC256(senhaToken))
                .withIssuer("unesp_sistema_veiculos")
                .build()
                .verify(token)
                .getSubject();

    }
}
