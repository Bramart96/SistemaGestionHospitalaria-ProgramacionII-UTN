package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;

/** Departamento de una especialidad, dueño de sus salas. */
public class Departamento {
    private final String nombre;
    private final EspecialidadMedica especialidad;
    private Hospital hospital;
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Sala> salas = new ArrayList<>();

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Validacion.texto(nombre, "Nombre del departamento");
        Validacion.requerido(especialidad, "Especialidad");
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }
    public EspecialidadMedica getEspecialidad() { return especialidad; }
    public Hospital getHospital() { return hospital; }

    /** Conserva la relación con el hospital desde las dos puntas. */
    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {
            if (this.hospital != null) {
                this.hospital.quitarDepartamentoInterno(this);
            }
            this.hospital = hospital;
            if (hospital != null) {
                hospital.vincularDepartamentoInterno(this);
            }
        }
    }

    public void agregarMedico(Medico medico) {
        Validacion.requerido(medico, "Médico");
        medico.setDepartamento(this);
    }

    void vincularMedicoInterno(Medico medico) {
        if (!medicos.contains(medico)) { medicos.add(medico); }
    }
    void quitarMedicoInterno(Medico medico) { medicos.remove(medico); }

    /** Crea y registra una sala propia del departamento (composición). */
    public Sala crearSala(String numero, String tipo) {
        Sala sala = new Sala(numero, tipo, this);
        salas.add(sala);
        return sala;
    }

    public List<Medico> getMedicos() { return Collections.unmodifiableList(medicos); }
    public List<Sala> getSalas() { return Collections.unmodifiableList(salas); }
}
