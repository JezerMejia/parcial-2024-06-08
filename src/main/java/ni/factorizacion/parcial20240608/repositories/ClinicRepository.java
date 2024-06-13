package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClinicRepository extends JpaRepository<AppointmentMedicSpecialty, Event.ID> {
    @Query("SELECT p FROM AppointmentMedicSpecialty p JOIN FETCH p.appointment a JOIN FETCH p.medic b JOIN FETCH p.specialty c")
    List<AppointmentMedicSpecialty> findByMedic();
}