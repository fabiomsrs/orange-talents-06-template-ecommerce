package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OpiniaoDto {
    @NotNull
    @Min(value = 1) @Max(value = 5)
    private Integer nota;
    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(max = 500)
    private String descricao;

    public OpiniaoDto(@NotNull @Min(value = 1) @Max(value = 5) Integer nota,
                      @NotNull @NotEmpty String titulo,
                      @NotNull @NotEmpty @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao converter(Usuario usuario, Produto produto){
        return new Opiniao(
                this.nota,
                this.titulo,
                this.descricao,
                usuario,
                produto);
    }
}
