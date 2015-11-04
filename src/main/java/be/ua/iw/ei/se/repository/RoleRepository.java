package be.ua.iw.ei.se.repository;

import be.ua.iw.ei.se.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Thomas on 22/10/2015.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
