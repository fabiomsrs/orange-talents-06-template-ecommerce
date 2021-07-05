package br.com.zupacademy.fabiano.mercadolivre.repository;

import br.com.zupacademy.fabiano.mercadolivre.modelo.StatusTransacao;
import br.com.zupacademy.fabiano.mercadolivre.modelo.TentativaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TentativaPagamentoRepository extends JpaRepository<TentativaPagamento, Long> {
    List<TentativaPagamento> findByTransacaoIdAndStatus(String transacaoId, StatusTransacao sucesso);
}
