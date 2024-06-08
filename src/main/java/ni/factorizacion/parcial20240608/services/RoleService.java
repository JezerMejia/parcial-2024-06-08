package ni.factorizacion.parcial20240608.services;

import ni.factorizacion.parcial20240608.domain.entities.Role;

public interface RoleService {
    void saveRole(Role role);

    long getCount();
}
