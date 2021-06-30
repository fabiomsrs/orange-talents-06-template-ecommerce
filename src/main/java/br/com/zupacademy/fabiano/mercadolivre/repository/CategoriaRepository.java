package br.com.zupacademy.fabiano.mercadolivre.repository;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
