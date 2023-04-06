package com.doitwell.group.springTesting.Boxes;

import com.doitwell.group.springTesting.RDV.RDV;
import com.doitwell.group.springTesting.Rooms.Rooms;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Boxes {

    @Id
    @SequenceGenerator(
            name = "boxes_sequence",
            sequenceName = "boxes_sequence",
            allocationSize =1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "boxes_sequence"
    )
    private Long id;
    private int boxeNumber;
/*
    @ManyToMany
    @JoinTable(
            name = "boxe_rdv",
            joinColumns = @JoinColumn(name = "rdv_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "boxe_id", referencedColumnName = "id")
    )
    private Collection<RDV> rdvs; */

    @OneToMany(mappedBy = "boxe")
    private Collection<BoxeRdv> boxeRdvs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "id"
    )
    private Rooms room;

    public Boxes() {
    }

    public Boxes(int boxeNumber, Rooms room) {
        this.boxeNumber = boxeNumber;
        this.room = Objects.requireNonNull(room);
    }

    public Long getId() {
        return id;
    }

    public int getBoxeNumber() {
        return boxeNumber;
    }

    public void setBoxeNumber(int boxeNumber) {
        this.boxeNumber = boxeNumber;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = Objects.requireNonNull(room);
    }
/*
    public Collection<RDV> getRdvs() {
        return rdvs;
    }

    public void setRdvs(Collection<RDV> rdvs) {
        this.rdvs = rdvs;
    }
*/
    public Collection<BoxeRdv> getBoxeRdvs() {
        return boxeRdvs;
    }

    public void setBoxeRdvs(Collection<BoxeRdv> boxeRdvs) {
        this.boxeRdvs = boxeRdvs;
    }
}
