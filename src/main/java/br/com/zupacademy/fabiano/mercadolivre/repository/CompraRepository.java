package br.com.zupacademy.fabiano.mercadolivre.repository;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
