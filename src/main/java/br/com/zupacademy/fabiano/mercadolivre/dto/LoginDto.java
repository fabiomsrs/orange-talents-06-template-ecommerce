package br.com.zupacademy.fabiano.mercadolivre.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {
    @NotNull
    @NotEmpty
    private String login;
    @NotNull
    @NotEmpty
    private String senha;

    public LoginDto() {
    }

    public LoginDto(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
