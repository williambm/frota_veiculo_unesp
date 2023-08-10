package br.unesp.frotaveiculos.adapters.controller;

import br.unesp.frotaveiculos.adapters.config.security.TokenManagerService;
import br.unesp.frotaveiculos.adapters.db.model.Funcionario;
import br.unesp.frotaveiculos.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManagerService tokenService;

    @PostMapping("/login")
    public ResponseEntity logar(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken usuarioSenha = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
        Authentication autenticacao = this.authenticationManager.authenticate(usuarioSenha);
        var usuario = (Funcionario) autenticacao.getPrincipal();

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok()
                .header("Authorization","Bearer "+token)
                .body(token);
    }
}
