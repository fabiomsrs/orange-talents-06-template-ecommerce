package br.com.zupacademy.fabiano.mercadolivre.repository;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
