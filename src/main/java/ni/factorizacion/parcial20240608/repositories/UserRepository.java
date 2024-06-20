package ni.factorizacion.parcial20240608.repositories;

import ni.factorizacion.parcial20240608.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findByUsername(String username);

    @Query("SELECT u from User u JOIN u.roles r WHERE r.id = 'DOCT'")
    List<User> findMedics();

    @Query("SELECT u from User u JOIN u.roles r WHERE r.id = 'PTNT'")
    List<User> findPatients();
}
