package org.jcr.enums;

/** Valores del dominio con su descripción para el usuario. */
public enum EstadoCita {
    PROGRAMADA("Programada"),
    EN_CURSO("En Curso"),
    COMPLETADA("Completada"),
    CANCELADA("Cancelada"),
    NO_ASISTIO("No Asistió");

    private final String descripcion;

    EstadoCita(String descripcion) {
        this.descripcion = descripcion;
    }

    /** Devuelve el texto asociado a la constante. */
    public String getDescripcion() {
        return descripcion;
    }
}
