package br.com.zupacademy.fabiano.mercadolivre.dto;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Compra;
import br.com.zupacademy.fabiano.mercadolivre.modelo.StatusTransacao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.TentativaPagamento;
import br.com.zupacademy.fabiano.mercadolivre.repository.CompraRepository;
import br.com.zupacademy.fabiano.mercadolivre.validator.ChecaExistencia;
import br.com.zupacademy.fabiano.mercadolivre.validator.PagamentoProcessado;

import javax.validation.constraints.NotNull;

@PagamentoProcessado
public class PayPalTentativaPagamentoDto {
    @NotNull
    @ChecaExistencia(identityField = "id", instanceClass = Compra.class)
    private Long compra;
    @NotNull
    private String transacaoId;
    @NotNull
    private String status;

    public PayPalTentativaPagamentoDto() {
    }

    public PayPalTentativaPagamentoDto(@NotNull Long compra,
                                       @NotNull String transacaoId,
                                       @NotNull String status) {
        this.compra = compra;
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Long getCompra() {
        return compra;
    }

    public void setCompra(Long compra) {
        this.compra = compra;
    }

    public String getTransacaoId() {
        return transacaoId;
    }

    public void setTransacaoId(String transacaoId) {
        this.transacaoId = transacaoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TentativaPagamento converter(CompraRepository compraRepository) {
        Compra compra = compraRepository.findById(this.compra).get();
        return new TentativaPagamento(compra, transacaoId, StatusTransacao.converter(this.status));
    }
}

