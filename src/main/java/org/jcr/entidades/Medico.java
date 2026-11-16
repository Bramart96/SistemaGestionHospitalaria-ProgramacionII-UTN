package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

/** Profesional con matrícula propia y una especialidad médica. */
public class Medico extends Persona {
    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                  TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        Validacion.requerido(especialidad, "Especialidad");
        this.matricula = new Matricula(numeroMatricula);
        this.especialidad = especialidad;
    }

    public Matricula getMatricula() { return matricula; }
    public EspecialidadMedica getEspecialidad() { return especialidad; }
    public Departamento getDepartamento() { return departamento; }

    /** Verifica la especialidad antes de mover al médico a otro departamento. */
    public void setDepartamento(Departamento departamento) {
        if (departamento != null && departamento.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException("La especialidad del médico no coincide con el departamento.");
        }
        if (this.departamento != departamento) {
            if (this.departamento != null) {
                this.departamento.quitarMedicoInterno(this);
            }
            this.departamento = departamento;
            if (departamento != null) {
                departamento.vincularMedicoInterno(this);
            }
        }
    }

    public void addCita(Cita cita) {
        Validacion.requerido(cita, "Cita");
        if (!citas.contains(cita)) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() { return Collections.unmodifiableList(citas); }
}
