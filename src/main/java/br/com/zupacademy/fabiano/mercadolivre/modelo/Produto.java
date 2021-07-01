package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ManyToOne
    private Usuario dono;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ElementCollection
    List<String> imagens;
    @OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private Set<Pergunta> perguntas = new HashSet<>();

    public Produto() {
    }

    public Produto(@NotNull @NotEmpty String nome,
                   @NotNull @DecimalMin(value = "0.0") Double valor,
                   @NotNull @Min(value = 0) Integer quantidadeDisponivel,
                   @Size(min = 3) Map<String, String> caracteristicas,
                   @NotNull @NotEmpty @Length(max = 400) String descricao,
                   @NotNull Categoria categoria,
                   Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = usuario;
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

    public Usuario getDono() {
        return dono;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Integer getTotalOpinioes(){
        return this.opinioes.size();
    }

    public Double getMediaNotas(){
        Integer soma =  this.opinioes.stream().map(Opiniao::getNota).collect(Collectors.summingInt(i->i));
        return Double.valueOf(soma / this.getTotalOpinioes());
    }

    public void adicionarImagens(Set<String> links) {
        this.imagens.addAll(links);
    }

    public boolean pertence(Usuario usuario) {
        return usuario.getId() == this.dono.getId();
    }
}
