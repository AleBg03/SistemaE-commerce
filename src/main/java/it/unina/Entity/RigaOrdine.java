package it.unina.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RigaOrdine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantita")
    private int quantità;
    private Float prezzoUnitario;
    @ManyToOne (optional = false)
    private Ordine ordine;

    @ManyToOne (optional = false)
    private Prodotto prodotto;

    protected RigaOrdine() {
    }

    public RigaOrdine(Prodotto prodotto, int quantità, Float prezzoUnitario) {
        this.prodotto = prodotto;
        this.quantità = quantità;
        this.prezzoUnitario = prezzoUnitario;
    }

    void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public int getQuantità() {
        return this.quantità;
    }

    public Float getPrezzoUnitario() {
        return this.prezzoUnitario;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }
}
