package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrecriptionRepository extends JpaRepository<Prescription, UUID> {
    /*@Query("SELECT p FROM Prescription p WHERE p.appointment.patient = :user")
    List<Prescription> findByAppointment_Patient(User patient);*/
}
