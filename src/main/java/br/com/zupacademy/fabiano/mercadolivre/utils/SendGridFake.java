package br.com.zupacademy.fabiano.mercadolivre.utils;

import org.springframework.stereotype.Component;

@Component
public class SendGridFake implements EnviadorEmail{
    @Override
    public void enviaEmail(String msg) {
        /*
        Envia email
        * */
        System.out.println(msg);
    }
}
