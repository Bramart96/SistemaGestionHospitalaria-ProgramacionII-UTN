package org.jcr.entidades;

import java.time.LocalDate;
import org.jcr.enums.TipoSangre;

/** Datos y comportamientos compartidos por pacientes y médicos. */
public abstract class Persona {
    private final String nombre;
    private final String apellido;
    private final String dni;
    private final LocalDate fechaNacimiento;
    private final TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni,
                   LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = Validacion.texto(nombre, "Nombre");
        this.apellido = Validacion.texto(apellido, "Apellido");
        if (dni == null || (dni.length() != 7 && dni.length() != 8)
                || !Validacion.digitos(dni)) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos.");
        }
        Validacion.requerido(fechaNacimiento, "Fecha de nacimiento");
        Validacion.requerido(tipoSangre, "Tipo de sangre");
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public TipoSangre getTipoSangre() { return tipoSangre; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    /** Calcula los años cumplidos, considerando el cumpleaños de este año. */
    public int getEdad() {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();
        if (fechaNacimiento.plusYears(edad).isAfter(hoy)) {
            edad--;
        }
        return edad;
    }
}
