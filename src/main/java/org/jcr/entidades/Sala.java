package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Sala creada por su departamento; registra sus citas. */
public class Sala {
    private final String numero;
    private final String tipo;
    private final Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Validacion.texto(numero, "Número de sala");
        this.tipo = Validacion.texto(tipo, "Tipo de sala");
        Validacion.requerido(departamento, "Departamento");
        this.departamento = departamento;
    }

    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public Departamento getDepartamento() { return departamento; }

    public void addCita(Cita cita) {
        Validacion.requerido(cita, "Cita");
        if (!citas.contains(cita)) { citas.add(cita); }
    }

    public List<Cita> getCitas() { return Collections.unmodifiableList(citas); }
}
