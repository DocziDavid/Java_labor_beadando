package com.example.java_labor_beadando.modelclasses;

import javax.persistence.*;

@Entity
@Table(name="nezo")
public class Nezok {
    @Id
    public int id;
    private String nev;
    private boolean ferfi;
    private boolean berletes;

    public Nezok(int id, String nev, boolean ferfi, boolean belepes) {
        this.id = id;
        this.nev = nev;
        this.ferfi = ferfi;
        this.berletes = berletes;
    }

    public Nezok() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public boolean isFerfi() {
        return ferfi;
    }

    public void setFerfi(boolean ferfi) {
        this.ferfi = ferfi;
    }

    public boolean isBerletes() {
        return berletes;
    }

    public void setBerletes(boolean belepes) {
        this.berletes = berletes;
    }
}
