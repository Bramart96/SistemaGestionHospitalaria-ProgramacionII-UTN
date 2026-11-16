package org.jcr.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

/** Gestiona citas en memoria, sin superposiciones de médicos ni salas. */
public class CitaManager implements CitaService {
    private static final int HORAS_ENTRE_CITAS = 2;
    private final List<Cita> citas = new ArrayList<>();

    /** Valida todos los requisitos antes de crear o registrar una cita. */
    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala,
                               LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null || medico == null || sala == null) {
            throw new CitaException("Paciente, médico y sala son obligatorios.");
        }
        if (fechaHora == null || fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La fecha debe ser actual o futura.");
        }
        if (!Double.isFinite(costo) || costo <= 0) {
            throw new CitaException("El costo debe ser positivo y finito.");
        }
        if (medico.getEspecialidad() != sala.getDepartamento().getEspecialidad()) {
            throw new CitaException("La especialidad del médico no coincide con la sala.");
        }
        verificarDisponibilidad(medico.getCitas(), fechaHora, "Médico");
        verificarDisponibilidad(sala.getCitas(), fechaHora, "Sala");

        Cita cita = new Cita(paciente, medico, sala, fechaHora, costo);
        citas.add(cita);
        paciente.addCita(cita);
        medico.addCita(cita);
        sala.addCita(cita);
        return cita;
    }

    /** Una diferencia exacta de dos horas es válida en ambas direcciones. */
    private void verificarDisponibilidad(List<Cita> existentes, LocalDateTime fecha,
                                          String recurso) throws CitaException {
        for (Cita cita : existentes) {
            LocalDateTime anterior = cita.getFechaHora();
            if (fecha.isAfter(anterior.minusHours(HORAS_ENTRE_CITAS))
                    && fecha.isBefore(anterior.plusHours(HORAS_ENTRE_CITAS))) {
                throw new CitaException(recurso + " sin disponibilidad en ese horario.");
            }
        }
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getPaciente() == paciente) { resultado.add(cita); }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getMedico() == medico) { resultado.add(cita); }
        }
        return resultado;
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita cita : citas) {
            if (cita.getSala() == sala) { resultado.add(cita); }
        }
        return resultado;
    }
}
