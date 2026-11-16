package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.TipoSangre;

/** Paciente con historia clínica propia y citas registradas. */
public class Paciente extends Persona {
    private final String telefono;
    private final String direccion;
    private final HistoriaClinica historiaClinica;
    private Hospital hospital;
    private final List<Cita> citas = new ArrayList<>();

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                    TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.telefono = Validacion.texto(telefono, "Teléfono");
        this.direccion = Validacion.texto(direccion, "Dirección");
        this.historiaClinica = new HistoriaClinica(this);
    }

    public String getTelefono() { return telefono; }
    public String getDireccion() { return direccion; }
    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public Hospital getHospital() { return hospital; }

    /** Cambia de hospital y sincroniza ambas puntas de la agregación. */
    public void setHospital(Hospital hospital) {
        if (this.hospital != hospital) {
            if (this.hospital != null) {
                this.hospital.quitarPacienteInterno(this);
            }
            this.hospital = hospital;
            if (hospital != null) {
                hospital.vincularPacienteInterno(this);
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
