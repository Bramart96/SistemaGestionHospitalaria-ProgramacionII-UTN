package org.jcr.entidades;

/** Centraliza las comprobaciones comunes de las entidades. */
final class Validacion {
    private Validacion() { }

    static String texto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        return valor.trim();
    }

    static void requerido(Object valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
    }

    static boolean digitos(String valor) {
        for (int i = 0; i < valor.length(); i++) {
            if (!Character.isDigit(valor.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
