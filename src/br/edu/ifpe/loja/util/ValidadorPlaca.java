package br.edu.ifpe.loja.util;

public class ValidadorPlaca {

    private static final String REGEX_PADRAO_ANTIGO = "^[A-Z]{3}-\\d{4}$";
    private static final String REGEX_PADRAO_MERCOSUL = "^[A-Z]{3}\\d[A-Z]\\d{2}$";

    public static boolean validarPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            return false;
        }
        return placa.matches(REGEX_PADRAO_ANTIGO) || placa.matches(REGEX_PADRAO_MERCOSUL);
    }
}
