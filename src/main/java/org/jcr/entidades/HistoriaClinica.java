package org.jcr.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Historia creada por su paciente; protege las listas de registros. */
public class HistoriaClinica {
    private final Paciente paciente;
    private final String numeroHistoria;
    private final LocalDateTime fechaCreacion;
    private final List<String> diagnosticos = new ArrayList<>();
    private final List<String> tratamientos = new ArrayList<>();
    private final List<String> alergias = new ArrayList<>();

    HistoriaClinica(Paciente paciente) {
        Validacion.requerido(paciente, "Paciente");
        this.paciente = paciente;
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria();
    }

    private String generarNumeroHistoria() {
        return "HC-" + paciente.getDni() + "-" + fechaCreacion.getYear();
    }

    public Paciente getPaciente() { return paciente; }
    public String getNumeroHistoria() { return numeroHistoria; }

    public void agregarDiagnostico(String diagnostico) { agregar(diagnosticos, diagnostico); }
    public void agregarTratamiento(String tratamiento) { agregar(tratamientos, tratamiento); }
    public void agregarAlergia(String alergia) { agregar(alergias, alergia); }

    /** Omite registros nulos o vacíos sin interrumpir la operación. */
    private void agregar(List<String> registros, String valor) {
        if (valor != null && !valor.isBlank()) {
            registros.add(valor.trim());
        }
    }

    public List<String> getDiagnosticos() { return Collections.unmodifiableList(diagnosticos); }
    public List<String> getTratamientos() { return Collections.unmodifiableList(tratamientos); }
    public List<String> getAlergias() { return Collections.unmodifiableList(alergias); }
}
