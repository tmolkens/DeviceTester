package be.ua.iw.ei.se.data;

import be.ua.iw.ei.se.model.Permission;
import be.ua.iw.ei.se.model.Role;
import be.ua.iw.ei.se.model.User;
import be.ua.iw.ei.se.repository.PermissionRepository;
import be.ua.iw.ei.se.repository.RoleRepository;
import be.ua.iw.ei.se.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 29/10/2015.
 */
@Service
@Profile("default")
public class DatabaseLoader {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(PermissionRepository permissionRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void initDatabase() {
        Permission p1 = new Permission("logon");
        permissionRepository.save(p1);
        Permission p2 = new Permission("manage users");
        permissionRepository.save(p2);
        Permission p3 = new Permission("manage own account");
        permissionRepository.save(p3);
        Role administrator = new Role("Administrator");
        Role tester = new Role("Tester");
        Role researcher = new Role("Researcher");
        List<Permission> permissions =  new ArrayList<Permission>();
        permissions.add(p1);
        permissions.add(p2);
        permissions.add(p3);
        administrator.setPermissions(permissions);
        roleRepository.save(administrator);
        permissions =  new ArrayList<Permission>();
        permissions.add(p1);
        permissions.add(p2);
        tester.setPermissions(permissions);
        roleRepository.save(tester);
        permissions =  new ArrayList<Permission>();
        permissions.add(p1);
        researcher.setPermissions(permissions);
        roleRepository.save(researcher);
        User u1 = new User("Edwin","Walsh");
        u1.setUserName("user1");
        u1.setPassword("edwin");
        List<Role> roles = new ArrayList<>();
        roles.add(administrator);
        u1.setRoles(roles);
        userRepository.save(u1);
        User u2 = new User("Filip","Van der Schueren");
        u2.setUserName("user2");
        u2.setPassword("filip");
        roles = new ArrayList<>();
        roles.add(tester);
        u2.setRoles(roles);
        userRepository.save(u2);
        User u3 = new User("Marijn","Temmerman");
        u3.setUserName("user3");
        u3.setPassword("marijn");
        roles = new ArrayList<>();
        roles.add(researcher);
        u3.setRoles(roles);
        userRepository.save(u3);
    }
}

