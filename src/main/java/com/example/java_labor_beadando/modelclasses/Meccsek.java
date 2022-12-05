package com.example.java_labor_beadando.modelclasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name="meccs")
public class Meccsek {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String datum;
    private String kezdes;
    private String tipus;
    private int belepo;

    public Meccsek(int id, String datum, String kezdes, String tipus, int belepo) {
        this.id = id;
        this.datum = datum;
        this.kezdes = kezdes;
        this.tipus = tipus;
        this.belepo = belepo;
    }

    public Meccsek() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getKezdes() {
        return kezdes;
    }

    public void setKezdes(String kezdes) {
        this.kezdes = kezdes;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getBelepo() {
        return belepo;
    }

    public void setBelepo(int belepo) {
        this.belepo = belepo;
    }
}


