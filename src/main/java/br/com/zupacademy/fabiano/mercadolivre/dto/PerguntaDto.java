package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PerguntaDto {
    @NotNull
    @NotEmpty
    private String titulo;

    public PerguntaDto() {
    }

    public PerguntaDto(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converter(Usuario usuario, Produto produto){
        return new Pergunta(titulo, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }
}
