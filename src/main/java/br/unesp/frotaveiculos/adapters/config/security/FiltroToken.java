package br.unesp.frotaveiculos.adapters.config.security;

import br.unesp.frotaveiculos.adapters.db.repository.FuncionarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroToken extends OncePerRequestFilter {

    @Autowired
    private TokenManagerService tokenManagerService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Pegamos do Header da requisição o Authorization com o token
        String token = request.getHeader("Authorization");

        if(token!=null || token!=""){
            token = token.replace("Bearer ", "");
            String subject = tokenManagerService.getSubject(token); //valida o token e retorna o subject que é o login

            UserDetails usuario = funcionarioRepository.findByEmail(subject); //Busca o usuário na base

            UsernamePasswordAuthenticationToken autenticado = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //reautentica o usuário

            SecurityContextHolder.getContext().setAuthentication(autenticado); //Coloca o usuáro no contexto de autenticação do spring
        }
        filterChain.doFilter(request,response); //Chama o proximo filtro que será os de rotas
    }
}
