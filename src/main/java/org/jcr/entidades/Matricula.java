package org.jcr.entidades;

/** Identificación profesional con formato MP- seguido de 4 a 6 dígitos. */
public class Matricula {
    private final String numero;

    public Matricula(String numero) {
        if (numero == null || !numero.startsWith("MP-")
                || numero.length() < 7 || numero.length() > 9
                || !Validacion.digitos(numero.substring(3))) {
            throw new IllegalArgumentException("Matrícula inválida: MP- y 4 a 6 dígitos.");
        }
        this.numero = numero;
    }

    public String getNumero() { return numero; }
}
