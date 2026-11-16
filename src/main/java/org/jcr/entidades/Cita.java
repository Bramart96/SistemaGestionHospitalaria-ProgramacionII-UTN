package org.jcr.entidades;

import java.time.LocalDateTime;
import org.jcr.enums.EstadoCita;

/** Cita médica con datos de programación y estado modificable. */
public class Cita {
    private final Paciente paciente;
    private final Medico medico;
    private final Sala sala;
    private final LocalDateTime fechaHora;
    private final double costo;
    private EstadoCita estado = EstadoCita.PROGRAMADA;
    private String observaciones = "";

    public Cita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) {
        Validacion.requerido(paciente, "Paciente");
        Validacion.requerido(medico, "Médico");
        Validacion.requerido(sala, "Sala");
        Validacion.requerido(fechaHora, "Fecha y hora");
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;
    }

    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public Sala getSala() { return sala; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public double getCosto() { return costo; }
    public EstadoCita getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }

    /** Valida antes de cambiar el estado, preservando el anterior ante un error. */
    public void setEstado(EstadoCita estado) {
        Validacion.requerido(estado, "Estado");
        this.estado = estado;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones == null ? "" : observaciones;
    }
}
