package br.com.zupacademy.fabiano.mercadolivre.modelo;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class TentativaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Compra compra;
    @NotNull
    private String transacaoId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public TentativaPagamento() {
    }

    public TentativaPagamento(Compra compra, String transacaoId, StatusTransacao status) {
        this.compra = compra;
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
