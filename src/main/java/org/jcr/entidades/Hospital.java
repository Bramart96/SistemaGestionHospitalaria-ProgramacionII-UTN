package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Hospital que agrupa departamentos y pacientes. */
public class Hospital {
    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final List<Departamento> departamentos = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Validacion.texto(nombre, "Nombre del hospital");
        this.direccion = Validacion.texto(direccion, "Dirección");
        this.telefono = Validacion.texto(telefono, "Teléfono");
    }

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }

    /** Delega la sincronización de la relación al departamento. */
    public void agregarDepartamento(Departamento departamento) {
        Validacion.requerido(departamento, "Departamento");
        departamento.setHospital(this);
    }

    /** Delega la sincronización de la relación al paciente. */
    public void agregarPaciente(Paciente paciente) {
        Validacion.requerido(paciente, "Paciente");
        paciente.setHospital(this);
    }

    // Métodos internos: no vuelven a invocar los setters de la otra punta.
    void vincularDepartamentoInterno(Departamento departamento) {
        if (!departamentos.contains(departamento)) { departamentos.add(departamento); }
    }
    void quitarDepartamentoInterno(Departamento departamento) { departamentos.remove(departamento); }
    void vincularPacienteInterno(Paciente paciente) {
        if (!pacientes.contains(paciente)) { pacientes.add(paciente); }
    }
    void quitarPacienteInterno(Paciente paciente) { pacientes.remove(paciente); }

    public List<Departamento> getDepartamentos() { return Collections.unmodifiableList(departamentos); }
    public List<Paciente> getPacientes() { return Collections.unmodifiableList(pacientes); }
}
