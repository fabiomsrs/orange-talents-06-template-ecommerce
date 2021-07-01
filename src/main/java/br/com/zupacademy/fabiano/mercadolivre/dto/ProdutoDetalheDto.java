package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Opiniao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Pergunta;
import br.com.zupacademy.fabiano.mercadolivre.modelo.Produto;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalheDto {
    private List<String> links;
    private String nome;
    private Double preco;
    private Map<String, String> caracteristicas;
    private String descricao;
    private Double mediaNota;
    private Integer totalDeNotas;
    private Set<Opiniao> opinioes;
    private Set<PerguntaDto> perguntas;

    public ProdutoDetalheDto(Produto produto) {
        this.links = produto.getImagens();
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas();
        this.descricao = produto.getDescricao();
        this.mediaNota = produto.getMediaNotas();
        this.totalDeNotas = produto.getTotalOpinioes();
        this.opinioes = produto.getOpinioes();
        this.perguntas = produto.getPerguntas().stream().map(PerguntaDto::new).collect(Collectors.toSet());
    }

    public List<String> getLinks() {
        return links;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaDto> getPerguntas() {
        return perguntas;
    }
}