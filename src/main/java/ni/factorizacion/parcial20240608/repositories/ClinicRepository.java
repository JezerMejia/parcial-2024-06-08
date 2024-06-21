package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentMedicSpecialty;
import ni.factorizacion.parcial20240608.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface ClinicRepository extends JpaRepository<AppointmentMedicSpecialty, Event.ID> {
    @Query("SELECT a from Appointment a JOIN a.appointmentMedicSpecialty s WHERE s.medic = :medic")
    List<Appointment> findByMedic(User medic);
}