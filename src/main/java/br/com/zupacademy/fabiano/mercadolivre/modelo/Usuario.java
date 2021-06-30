package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
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
