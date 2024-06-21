package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.History;
import ni.factorizacion.parcial20240608.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<History, UUID> {
    Optional<History> findByDate(LocalDateTime dateTime);
    Optional<History> findByReason(String Reason);
    Integer countByPatient(User patient);
    List<History> findByPatient(User patient);
}
