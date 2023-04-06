package com.doitwell.group.springTesting.User.Privilege;

import com.doitwell.group.springTesting.User.Roles.RolesUsers;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Privilege {

    @Id
    @SequenceGenerator(
            name = "privilege_sequence",
            sequenceName = "privilege_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "privilege_sequence"
    )
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<RolesUsers> roles;

    public Privilege(){}

    public Privilege(String name){
        this.name = Objects.requireNonNull(name);
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
}
