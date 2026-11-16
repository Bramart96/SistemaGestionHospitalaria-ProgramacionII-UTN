package org.jcr.enums;

/** Valores del dominio con su descripción para el usuario. */
public enum EspecialidadMedica {
    CARDIOLOGIA("Cardiología"),
    NEUROLOGIA("Neurología"),
    PEDIATRIA("Pediatría"),
    TRAUMATOLOGIA("Traumatología"),
    GINECOLOGIA("Ginecología"),
    UROLOGIA("Urología"),
    OFTALMOLOGIA("Oftalmología"),
    DERMATOLOGIA("Dermatología"),
    PSIQUIATRIA("Psiquiatría"),
    MEDICINA_GENERAL("Medicina General"),
    CIRUGIA_GENERAL("Cirugía General"),
    ANESTESIOLOGIA("Anestesiología");

    private final String descripcion;

    EspecialidadMedica(String descripcion) {
        this.descripcion = descripcion;
    }

    /** Devuelve el texto asociado a la constante. */
    public String getDescripcion() {
        return descripcion;
    }
}
