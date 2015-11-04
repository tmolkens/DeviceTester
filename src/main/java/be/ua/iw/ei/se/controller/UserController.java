package be.ua.iw.ei.se.controller;

import be.ua.iw.ei.se.model.Permission;
import be.ua.iw.ei.se.model.Role;
import be.ua.iw.ei.se.model.User;
import be.ua.iw.ei.se.repository.PermissionRepository;
import be.ua.iw.ei.se.repository.RoleRepository;
import be.ua.iw.ei.se.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 30/10/2015.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @ModelAttribute("user")
    public User getUser() {
        return new User("","");
    }

    @RequestMapping(value="/users/list", method= RequestMethod.GET)
    public String showUsers(final ModelMap model){
        model.addAttribute("allUsers", userRepository.findAll());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "userpage";
    }

    @RequestMapping(value="/users/add", method= RequestMethod.POST)
    @PreAuthorize("hasRole('manage users')")
    public String addUser(@ModelAttribute User user){
        if(this.userRepository.findByUserName(user.getUserName()) != null) {
            System.out.print("Username already taken");
            return "newuserfail";
        } else {
            String newRole = user.getRole();
            addRoleAndUser(newRole, user);
            System.out.print("User added\n");
            return "redirect:/users/list";
        }
    }

    @RequestMapping(value="/users/delete")
    public String deleteUser(@ModelAttribute User user, final ModelMap model){
        User deletedUser = userRepository.findByUserName(user.getUserName());
        userRepository.delete(deletedUser.getId());
        model.clear();
        return "redirect:/users/list";
    }

    @RequestMapping(value="/users/edit", method=RequestMethod.POST)
    @PreAuthorize("hasRole('manage users')")
    public String editUser(@ModelAttribute User user) {
        System.out.print("User edited\n");
        if(this.userRepository.findByUserName(user.getNewUserName()) != null) {
            System.out.print("Username already taken");
            return "newuserfail";
        } else {
            Long userID = this.userRepository.findByUserName(user.getUserName()).getId();
            this.userRepository.delete(userID);
            User editedUser = new User(user.getFirstName(), user.getLastName());
            editedUser.setId(userID);
            editedUser.setUserName(user.getNewUserName());
            editedUser.setPassword(user.getPassword());
            editedUser.setFirstName(user.getFirstName());
            editedUser.setLastName(user.getLastName());
            addRoleAndUser(user.getRole(),editedUser);
            return "redirect:/users/list";
        }
    }

    @RequestMapping({"/users/retry"})
    public String retry(final ModelMap model) {
        return "redirect:/users/list";
    }

    //MOET ACHTERAF DYNAMISCH GEBEUREN, ROL UITLEZEN EN BIJHORENDE PERMISSIES TOEVOEGEN
    public void addRoleAndUser(String role, User user) {;
        switch (role) {
            case "Administrator":
               if (this.roleRepository.findByName(role) == null) {
                    Permission p1 = new Permission("logon");
                    permissionRepository.save(p1);
                    Permission p2 = new Permission("manage users");
                    permissionRepository.save(p2);
                    Permission p3 = new Permission("manage own account");
                    permissionRepository.save(p3);
                    Role administrator = new Role("Administrator");
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(p1);
                    permissions.add(p2);
                    permissions.add(p3);
                    administrator.setPermissions(permissions);
                    roleRepository.save(administrator);
                    List<Role> roles = new ArrayList<>();
                    roles.add(administrator);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                else {
                    Role administrator = this.roleRepository.findByName(role);
                    List<Role> roles = new ArrayList<>();
                    roles.add(administrator);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                break;
            case "Tester":
                if (this.roleRepository.findByName(role) == null) {
                    Permission p1 = new Permission("logon");
                    permissionRepository.save(p1);
                    Permission p2 = new Permission("manage users");
                    permissionRepository.save(p2);
                    Role tester = new Role("Tester");
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(p1);
                    permissions.add(p2);
                    tester.setPermissions(permissions);
                    roleRepository.save(tester);
                    List<Role> roles = new ArrayList<>();
                    roles.add(tester);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                else {
                    Role tester = this.roleRepository.findByName(role);
                    List<Role> roles = new ArrayList<>();
                    roles.add(tester);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                break;
            case "Researcher":
                if (this.roleRepository.findByName(role) == null) {
                    Permission p1 = new Permission("logon");
                    permissionRepository.save(p1);
                    Role researcher = new Role("Researcher");
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(p1);
                    researcher.setPermissions(permissions);
                    roleRepository.save(researcher);
                    List<Role> roles = new ArrayList<>();
                    roles.add(researcher);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                else {
                    Role researcher = this.roleRepository.findByName(role);
                    List<Role> roles = new ArrayList<>();
                    roles.add(researcher);
                    user.setRoles(roles);
                    userRepository.save(user);
                }
                break;
        }
    }
}
