package com.doitwell.group.springTesting.Rooms;

import com.doitwell.group.springTesting.Boxes.Boxes;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Rooms {
    @Id
    @SequenceGenerator(
            name = "rooms_sequence",
            sequenceName = "rooms_sequence",
            allocationSize =1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rooms_sequence"
    )
    private Long id;

    private String name;

    @OneToMany(mappedBy = "room",orphanRemoval = true)
    private Collection<Boxes> boxes;

    public Rooms() {
    }

    public Rooms(String name) {
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
}
