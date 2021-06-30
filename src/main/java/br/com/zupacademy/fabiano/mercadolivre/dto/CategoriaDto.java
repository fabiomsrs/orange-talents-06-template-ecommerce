package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Categoria;
import br.com.zupacademy.fabiano.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.fabiano.mercadolivre.validator.ChecaExistencia;
import br.com.zupacademy.fabiano.mercadolivre.validator.ValorUnico;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaDto {
    @NotNull
    @NotEmpty
    @ValorUnico(field = "nome", instanceClass = Categoria.class)
    private String nome;
    @ChecaExistencia(identityField = "id", instanceClass = Categoria.class)
    private Long categoriaMae;

    public CategoriaDto() {
    }

    public CategoriaDto(@NotNull @NotEmpty String nome,
                        Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    public Categoria converter(CategoriaRepository repository) {
        Categoria categoria = null;
        if(this.categoriaMae != null){
            categoria = repository.findById(this.categoriaMae).get();
        }
        return new Categoria(this.nome, categoria);
    }
}
