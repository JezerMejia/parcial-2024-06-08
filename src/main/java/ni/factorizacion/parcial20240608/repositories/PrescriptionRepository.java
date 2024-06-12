package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.dtos.PrescriptionSimpleDto;
import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import ni.factorizacion.parcial20240608.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PrescriptionRepository extends JpaRepository<Prescription, String> {

    @Query("SELECT p FROM Prescription p JOIN p.appointment a WHERE a.patient = :user")
    List<Prescription> findAllByUser(Optional<User> user);
}