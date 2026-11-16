package org.jcr.excepciones;

/** Excepción verificada para rechazos de programación de citas. */
public class CitaException extends Exception {
    private static final long serialVersionUID = 1L;

    public CitaException(String message) { super(message); }
    public CitaException(String message, Throwable cause) { super(message, cause); }
}
