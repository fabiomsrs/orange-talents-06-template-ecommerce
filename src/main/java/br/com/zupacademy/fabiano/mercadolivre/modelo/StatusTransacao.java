package br.com.zupacademy.fabiano.mercadolivre.modelo;

import java.util.HashMap;
import java.util.Map;

public enum StatusTransacao {
    SUCESSO,
    ERROR;

    public static StatusTransacao converter(String status){
        Map<String, StatusTransacao> mapper = new HashMap<String, StatusTransacao>();
        mapper.put("1", StatusTransacao.SUCESSO);
        mapper.put("0", StatusTransacao.ERROR);

        return mapper.get(status);
    }
}
