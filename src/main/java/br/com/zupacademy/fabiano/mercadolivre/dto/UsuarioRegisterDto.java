package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;

public class UsuarioRegisterDto {
    @NotNull
    @NotEmpty
    @Email
    private String login;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    private String senha;

    public UsuarioRegisterDto(){
    }

    public UsuarioRegisterDto(@NotNull @NotEmpty @Email String login,
                   @NotNull @NotEmpty @Size(min = 6)String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
        String senha = new BCryptPasswordEncoder().encode(this.senha);
        return new Usuario(this.login, senha);
    }
}
