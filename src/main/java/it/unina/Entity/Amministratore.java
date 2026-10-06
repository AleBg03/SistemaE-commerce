package it.unina.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Amministratore extends Utente_Registrato {
    @Column(name = "admin_id")
    private int id;

    protected Amministratore() {
    }

    public Amministratore(String email, String password, int id) {
        super(email, password, RUOLO_AMMINISTRATORE);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
