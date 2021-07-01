package br.com.zupacademy.fabiano.mercadolivre.utils;

import br.com.zupacademy.fabiano.mercadolivre.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class SendGridFake implements EnviadorEmail{
    @Override
    public void enviaEmail(Usuario usuario) {
        /*
        Envia email pro login do usuario
        * */
        System.out.println("Email enviado para " + usuario.getLogin());
    }
}
