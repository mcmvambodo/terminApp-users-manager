package com.doitwell.group.springTesting.RDV;

import com.doitwell.group.springTesting.Boxes.BoxeRdv;
import com.doitwell.group.springTesting.Boxes.Boxes;
import com.doitwell.group.springTesting.User.UserModel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
public class RDV {

    @Id
    @SequenceGenerator(
            name = "rdv_sequence",
            sequenceName = "rdv_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
            generator = "rdv_sequence"
    )
    private Long id;
    private String motif;
    private LocalDateTime rdv_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private UserModel user;

    private int status = 0;

    @OneToMany(mappedBy = "rdv")
    private Collection<BoxeRdv> boxeRdvs;

    public RDV() {
    }

    public RDV(String motif, LocalDateTime rdv_date, UserModel user)
    {
        this.motif = motif;
        this.rdv_date = rdv_date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDateTime getRdv_date() {
        return rdv_date;
    }

    public void setRdv_date(LocalDateTime rdv_date) {
        this.rdv_date = rdv_date;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<BoxeRdv> getBoxeRdvs() {
        return boxeRdvs;
    }

    public void setBoxeRdvs(Collection<BoxeRdv> boxeRdvs) {
        this.boxeRdvs = boxeRdvs;
    }
}
