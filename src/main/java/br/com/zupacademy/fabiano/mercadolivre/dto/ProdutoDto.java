package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Categoria;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.fabiano.mercadolivre.validator.ChecaExistencia;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Map;

public class ProdutoDto {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @DecimalMin(value = "0.0")
    private Double valor;
    @NotNull
    @Min(value = 0)
    private Integer quantidadeDisponivel;
    @Size(min = 3)
    private Map<String, String> caracteristicas;
    @NotNull
    @NotEmpty
    @Length(max = 400)
    private String descricao;
    @NotNull
    @ChecaExistencia(identityField = "id", instanceClass = Categoria.class)
    private Long categoria;

    public ProdutoDto() {
    }

    public ProdutoDto(@NotNull @NotEmpty String nome,
                      @NotNull @DecimalMin(value = "0.0") Double valor,
                      @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                      @Size(min = 3) Map<String, String> caracteristicas,
                      @NotNull @NotEmpty @Length(max = 400) String descricao,
                      @NotNull Long categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
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

    public Long getCategoria() {
        return categoria;
    }

    public Produto converter(CategoriaRepository repository) {
        Categoria categoria = repository.findById(this.categoria).get();
        return new Produto(
                this.nome,
                this.valor,
                this.quantidadeDisponivel,
                this.caracteristicas,
                this.descricao,
                categoria
        );
    }
}
