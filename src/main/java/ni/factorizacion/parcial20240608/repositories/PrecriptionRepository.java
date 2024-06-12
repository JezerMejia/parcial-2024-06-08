package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrecriptionRepository extends JpaRepository<Prescription, String> {
}
