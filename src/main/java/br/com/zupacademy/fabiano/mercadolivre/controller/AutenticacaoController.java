package br.com.zupacademy.fabiano.mercadolivre.controller;

import br.com.zupacademy.fabiano.mercadolivre.authentication.TokenService;
import br.com.zupacademy.fabiano.mercadolivre.dto.LoginDto;
import br.com.zupacademy.fabiano.mercadolivre.dto.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginDto dto){
        UsernamePasswordAuthenticationToken dadosLogin = dto.converter();
        try {
            System.out.println(dadosLogin);
            Authentication authentication =  this.authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "bearer"));
        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
