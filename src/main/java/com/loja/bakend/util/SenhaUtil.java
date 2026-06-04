package com.loja.bakend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtil {

    private static final BCryptPasswordEncoder encoder =

            new BCryptPasswordEncoder();

    public static String criptografar(

            String senha) {

        return encoder.encode(
            senha
        );
    }

    public static boolean verificar(

            String senha,

            String hash) {

        return encoder.matches(
            senha,
            hash
        );
    }
}
