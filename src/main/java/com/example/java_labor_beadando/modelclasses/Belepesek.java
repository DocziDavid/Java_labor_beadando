package com.example.java_labor_beadando.modelclasses;

import javax.persistence.*;

@Entity
@Table(name="belepes")
public class Belepesek {
    @Id
    private int nezoid;
    private int meccsid;
    private String idopont;

    public Belepesek(int nezoid, int meccsid, String idopont) {
        this.nezoid = nezoid;
        this.meccsid = meccsid;
        this.idopont = idopont;
    }

    public Belepesek() {
    }

    public int getNezoid() {
        return nezoid;
    }

    public void setNezoid(int nezoid) {
        this.nezoid = nezoid;
    }

    public int getMeccsid() {
        return meccsid;
    }

    public void setMeccsid(int meccsid) {
        this.meccsid = meccsid;
    }

    public String getIdopont() {
        return idopont;
    }

    public void setIdopont(String idopont) {
        this.idopont = idopont;
    }
}
