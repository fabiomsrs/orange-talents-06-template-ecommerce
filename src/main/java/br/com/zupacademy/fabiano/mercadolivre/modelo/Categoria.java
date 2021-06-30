package br.com.zupacademy.fabiano.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    public Categoria() {
    }

    public Categoria(@NotNull @NotEmpty String nome,
                     Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
