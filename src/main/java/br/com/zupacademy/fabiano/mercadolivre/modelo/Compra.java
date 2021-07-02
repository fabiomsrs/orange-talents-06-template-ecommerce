package br.com.zupacademy.fabiano.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Integer quantidade;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @ManyToOne
    private Usuario comprador;

    public Compra() {
    }

    public Compra(@NotNull Integer quantidade,
                  @NotNull GatewayPagamento gatewayPagamento,
                  @NotNull StatusCompra status,
                  @NotNull Produto produto,
                  @NotNull Usuario comprador) {
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
        this.status = status;
        this.produto = produto;
        this.comprador = comprador;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
