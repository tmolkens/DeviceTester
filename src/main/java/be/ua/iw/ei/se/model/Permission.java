package be.ua.iw.ei.se.model;

import javax.persistence.Entity;

@Entity
public class Permission extends MyAbstractPersistable<Long> {
    private String name;

    public Permission(){}
    public Permission(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

