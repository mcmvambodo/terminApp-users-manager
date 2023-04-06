package com.doitwell.group.springTesting.User.Roles;

import com.doitwell.group.springTesting.User.Privilege.Privilege;
import com.doitwell.group.springTesting.User.UserModel;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class RolesUsers {

    @Id
    @SequenceGenerator(
            name = "ROLES_SEQUENCE",
            sequenceName = "ROLES_SEQUENCE",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ROLES_SEQUENCE"
    )
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserModel> users;

    @ManyToMany()
    @JoinTable(
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Collection<Privilege> privileges;

    public RolesUsers() {
    }

    public RolesUsers(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserModel> users) {
        this.users = users;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Collection<Privilege> privileges) {
        this.privileges = privileges;
    }
}
