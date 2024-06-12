package ni.factorizacion.parcial20240608.services.impl;

import ni.factorizacion.parcial20240608.domain.entities.Role;
import ni.factorizacion.parcial20240608.repositories.RoleRepository;
import ni.factorizacion.parcial20240608.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public long getCount() {
        return roleRepository.count();
    }
}
