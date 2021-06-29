package br.com.zupacademy.fabiano.mercadolivre.repository;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
