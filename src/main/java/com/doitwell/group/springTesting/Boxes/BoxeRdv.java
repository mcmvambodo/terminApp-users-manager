package com.doitwell.group.springTesting.Boxes;

import com.doitwell.group.springTesting.RDV.RDV;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;

@Entity
public class BoxeRdv {

    @EmbeddedId
    private BoxeRdvId id = new BoxeRdvId();

    @ManyToOne
    @MapsId("boxeId")
    private Boxes boxe;

    @ManyToOne
    @MapsId("rdvId")
    private RDV rdv;

    private LocalDateTime startAt = LocalDateTime.now();
    private LocalDateTime endtAt;

    public BoxeRdv() {
    }

    public BoxeRdv(BoxeRdvId id, LocalDateTime startAt, LocalDateTime endtAt) {
        this.id = id;
        this.startAt = startAt;
        this.endtAt = endtAt;
    }

    public BoxeRdvId getId() {
        return id;
    }

    public void setId(BoxeRdvId id) {
        this.id = id;
    }

    public Boxes getBoxe() {
        return boxe;
    }

    public void setBoxe(Boxes boxe) {
        this.boxe = boxe;
    }

    public RDV getRdv() {
        return rdv;
    }

    public void setRdv(RDV rdv) {
        this.rdv = rdv;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndtAt() {
        return endtAt;
    }

    public void setEndtAt(LocalDateTime endtAt) {
        this.endtAt = endtAt;
    }
}
