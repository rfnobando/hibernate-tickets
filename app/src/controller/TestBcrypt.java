package controller;

import org.mindrot.jbcrypt.BCrypt;

public class TestBcrypt {
    public static void main(String[] args) {
        // Para registrar
        String hashed = BCrypt.hashpw("123456", BCrypt.gensalt());

        // Para verificar en login
        boolean isMatch = BCrypt.checkpw("123456", hashed);
        System.out.println(hashed);
        System.out.println(isMatch);
    }
}