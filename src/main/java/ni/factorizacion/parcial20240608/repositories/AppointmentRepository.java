package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.Appointment;
import ni.factorizacion.parcial20240608.domain.entities.AppointmentState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByStatus(AppointmentState status);
}
