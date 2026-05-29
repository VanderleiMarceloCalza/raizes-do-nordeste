package com.loja.bakend.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    public Map<String, String> processarPagamento() {

        Map<String, String> retorno =
            new HashMap<>();

        retorno.put(
            "status",
            "APROVADO"
        );

        String codigo =

        	    "PAY" +

        	    System.currentTimeMillis();

        	retorno.put(
        	    "codigo",
        	    codigo
        	);

        retorno.put(
            "mensagem",
            "Pagamento aprovado"
        );

        return retorno;
    }
}