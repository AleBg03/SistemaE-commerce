package it.unina.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(length = 255)
    private String descrizione;
    private Float prezzo;
    private int qtaDisponibile;
    private Boolean scontato;
    private Boolean disponibile;
    private int percentualeSconto;
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    protected Prodotto() {
    }

    public Prodotto(String nome, String descrizione, Float prezzo, int quantità, Categoria categoria) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.qtaDisponibile = quantità;
        this.scontato = false;
        this.disponibile = true;
        this.percentualeSconto = 0;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public int getQtaDisponibile() {
        return this.qtaDisponibile;
    }

    public Float getPrezzo() {
        return this.prezzo;
    }

    public Boolean getScontato() {
        return this.scontato;
    }

    public Boolean getDisponibile() {
        return this.disponibile;
    }

    public int getPercentualeSconto() {
        return percentualeSconto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void modificaProdotto(Prodotto aProdotto) {
        throw new UnsupportedOperationException();
    }

    public Boolean isDisponibile(Object aNome) {
        throw new UnsupportedOperationException();
    }

    public Float calcolaSconto() {
        throw new UnsupportedOperationException();
    }
}
