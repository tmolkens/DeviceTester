package be.ua.iw.ei.se.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 8/10/2015.
 */
@Entity
public class User extends MyAbstractPersistable<Long> {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String role;
    private String newUserName;
    @ManyToMany
    @JoinTable(
            name="USER_ROLE",
            joinColumns={@JoinColumn(name="USER_ID",
                    referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID",
                    referencedColumnName="ID")})

    private List<Role> roles;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = "";
        this.password = "";
        this.role = "";
        this.newUserName = "";
        roles = new ArrayList<>();
    }

    public User(String firstName, String lastName, String userName, String password, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
        this.role = "";
        this.newUserName = "";
    }

    public User() {
        roles = new ArrayList<>();
        userName = "";
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public List<Role> getRoles(){return roles;}
    public void setRoles(List<Role> roles){ this.roles = roles;}
    public void setUserName(String userName){this.userName = userName;}
    public String getUserName(){
        return userName;
    }
    public void setPassword(String password){this.password = password;}
    public String getPassword(){
        return password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getNewUserName() {
        return newUserName;
    }
    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userName.equals(user.userName);

    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }



}
