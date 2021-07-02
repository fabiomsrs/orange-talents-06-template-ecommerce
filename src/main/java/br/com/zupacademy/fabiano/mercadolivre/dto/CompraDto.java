package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.*;
import br.com.zupacademy.fabiano.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.fabiano.mercadolivre.validator.ChecaExistencia;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraDto {
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull
    private GatewayPagamento gatewayPagamento;
    private StatusCompra status = StatusCompra.INICIADA;
    @NotNull
    @ChecaExistencia(identityField = "id", instanceClass = Produto.class)
    private Long produto;

    public CompraDto() {
    }

    public CompraDto(@NotNull @Positive Integer quantidade,
                     @NotNull GatewayPagamento gatewayPagamento,
                     @NotNull Long produto) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.status = StatusCompra.INICIADA;
        this.produto = produto;
    }

    public Compra converter(ProdutoRepository repository, Usuario usuario){
        Produto produto = repository.findById(this.produto).get();
        return new Compra(
                this.quantidade,
                this.gatewayPagamento,
                this.status,
                produto,
                usuario
        );
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public Long getProduto() {
        return produto;
    }
}
