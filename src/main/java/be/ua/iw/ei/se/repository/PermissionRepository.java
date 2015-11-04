package be.ua.iw.ei.se.repository;

import be.ua.iw.ei.se.model.Permission;
import be.ua.iw.ei.se.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Thomas on 29/10/2015.
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    @Query(value = "select p from User u left join u.roles r left join r.permissions  p where u=:usr")
    Iterable<Permission> findAllForUser(@Param("usr") User user);
}