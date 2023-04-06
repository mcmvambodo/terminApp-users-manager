package com.doitwell.group.springTesting.Boxes;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BoxeRdvId implements Serializable {
    private final static long SerialVersionUID =1L;

    private Long boxeId;
    private Long rdvId;

    public BoxeRdvId(Long boxeId, Long rdvId) {
        this.boxeId = boxeId;
        this.rdvId = rdvId;
    }

    public BoxeRdvId() {
    }

    public Long getBoxeId() {
        return boxeId;
    }

    public void setBoxeId(Long boxeId) {
        this.boxeId = boxeId;
    }

    public Long getRdvId() {
        return rdvId;
    }

    public void setRdvId(Long rdvId) {
        this.rdvId = rdvId;
    }
}
