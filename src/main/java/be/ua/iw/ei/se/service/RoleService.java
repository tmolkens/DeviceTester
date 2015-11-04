package be.ua.iw.ei.se.service;

import be.ua.iw.ei.se.model.Role;
import be.ua.iw.ei.se.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Thomas on 4/11/2015.
 */
@Service
public class RoleService{
    @Autowired
    private RoleRepository roleRepository;
    public Iterable<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public void add(final Role role) {
        this.roleRepository.save(role);
    }
    public void delete(Long id) {
        this.roleRepository.delete(id);
    }

    public Role findByName(String name) {return roleRepository.findByName(name);
    }
}