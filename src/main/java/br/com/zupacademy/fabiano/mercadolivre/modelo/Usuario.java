package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false)
    private String login;
    @NotNull
    @NotEmpty
    @Size(min = 6)
    @Column(nullable = false)
    private String senha;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Usuario(){
    }

    public Usuario(@NotNull @NotEmpty @Email String login,
                   @NotNull @NotEmpty @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
