package it.unina.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private String civico;
    private int cap;
    @Column(name = "citta")
    private String città;

    protected Indirizzo() {
    }

    public Indirizzo(String via, String civico, int cap, String città) {
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.città = città;
    }

    public Indirizzo getIndirizzo() {
        return this;
    }

    public void setIndirizzo(String aVia, String aCivico, int aCap, String aCittà) {
        this.via = aVia;
        this.civico = aCivico;
        this.cap = aCap;
        this.città = aCittà;
    }

    @Override
    public String toString() {
        return via + " " + civico + ", " + String.format("%05d", cap) + " " + città;
    }
}
