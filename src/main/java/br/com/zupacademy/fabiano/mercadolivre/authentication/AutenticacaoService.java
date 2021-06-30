package br.com.zupacademy.fabiano.mercadolivre.authentication;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import br.com.zupacademy.fabiano.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByLogin(login);
        if (!usuario.isEmpty()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("dados inválidos");
    }

}
