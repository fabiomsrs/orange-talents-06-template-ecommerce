package br.com.zupacademy.fabiano.mercadolivre.utils;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;

public interface EnviadorEmail {
    void enviaEmail(Usuario usuario);
}
