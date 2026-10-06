package it.unina.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float totComplessivo = 0f;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreazione;

    @ManyToOne
    private Indirizzo indirizzo;

    @Enumerated(EnumType.STRING)
    private Stato stato;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_email")
    private Cliente cliente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
    private List<RigaOrdine> righe = new ArrayList<>();

    protected Ordine() {
    }

    public Ordine(Cliente cliente, Indirizzo indirizzo, Date dataCreazione, Stato stato) {
        this.cliente = cliente;
        this.indirizzo = indirizzo;
        this.dataCreazione = dataCreazione;
        this.stato = stato;
    }

    public void aggiungiRiga(RigaOrdine riga) {
        riga.setOrdine(this);
        righe.add(riga);
        totComplessivo += riga.getPrezzoUnitario() * riga.getQuantità();
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<RigaOrdine> getRighe() {
        return new ArrayList<>(righe);
    }

    public Date getDataCreazione() {
        return this.dataCreazione;
    }

    public Stato getStato() {
        return this.stato;
    }

    public Indirizzo getIndirizzo() {
        return this.indirizzo;
    }

    public Float getTotComplessivo() {
        return this.totComplessivo;
    }

    public void aggiornaStato(int aId, String aStato) {
        throw new UnsupportedOperationException();
    }

    public void annullaOrdine(int aId) {
        throw new UnsupportedOperationException();
    }

    public void consultaDettaglioOrdine(Ordine aOrdine) {
        throw new UnsupportedOperationException();
    }

    public Ordine getOrdini() {
        throw new UnsupportedOperationException();
    }
}
