package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.entities.Role;

import java.util.Optional;

public interface RoleService {
    void saveRole(Role role);

    long getCount();

    Optional<Role> findById(String id);
}
