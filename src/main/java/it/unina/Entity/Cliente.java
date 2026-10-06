package it.unina.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Utente_Registrato {
    private String nome;
    private String cognome;
    private String immagineProfilo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_email")
    private List<Indirizzo> indirizzi = new ArrayList<>();
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, optional = false)
    private Carrello carrello;
    @OneToMany(mappedBy = "cliente")
    private List<Ordine> ordini = new ArrayList<>();

    protected Cliente() {
    }

    public Cliente(String nome, String cognome, String email, String password) {
        super(email, password, RUOLO_CLIENTE);
        this.nome = nome;
        this.cognome = cognome;
        this.carrello = new Carrello(this);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String aNome) {
        this.nome = aNome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String aCognome) {
        this.cognome = aCognome;
    }

    public String getImmagineProfilo() {
        return this.immagineProfilo;
    }

    public void setImmagineProfilo(String aImmagineProfilo) {
        this.immagineProfilo = aImmagineProfilo;
    }

    public void modificaProfilo() {
        throw new UnsupportedOperationException();
    }

    public void effettuaOrdine() {
        throw new UnsupportedOperationException();
    }

    public void confermaOrdine() {
        throw new UnsupportedOperationException();
    }
}
