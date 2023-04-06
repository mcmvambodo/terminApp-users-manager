package com.doitwell.group.springTesting.User;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class Addresse {

    private String city;
    private String addresse;
    private String po_box;

    public Addresse() {
    }

    public Addresse(String city, String addresse, String po_box) {
        this.city = city;
        this.addresse = addresse;
        this.po_box = po_box;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getPo_box() {
        return po_box;
    }

    public void setPo_box(String po_box) {
        this.po_box = po_box;
    }
}
