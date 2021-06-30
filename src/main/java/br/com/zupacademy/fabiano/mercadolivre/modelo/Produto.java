package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String nome;
    @NotNull
    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private Double valor;
    @NotNull
    @Min(value = 0)
    @Column(nullable = false)
    private Integer quantidadeDisponivel;
    @ElementCollection
    @MapKeyColumn(name = "descricao")
    @Size(min = 3)
    private Map<String, String> caracteristicas;
    @NotNull
    @NotEmpty
    @Length(max = 400)
    private String descricao;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Produto() {
    }

    public Produto(@NotNull @NotEmpty String nome,
                   @NotNull @DecimalMin(value = "0.0") Double valor,
                   @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                   @Size(min = 3) Map<String, String> caracteristicas,
                   @NotNull @NotEmpty @Length(max = 400) String descricao,
                   @NotNull Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
