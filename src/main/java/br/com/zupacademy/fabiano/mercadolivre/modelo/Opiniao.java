package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(value = 1) @Max(value = 5)
    private Integer nota;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(max = 500)
    @Column(length = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    private Produto produto;

    public Opiniao() {
    }

    public Opiniao(@NotNull @Min(value = 1) @Max(value = 5) Integer nota,
                   @NotNull @NotEmpty String titulo,
                   @NotNull @NotEmpty @Length(max = 500) String descricao,
                   @NotNull Usuario usuario,
                   @NotNull Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
