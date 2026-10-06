package it.unina.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_utente")

public class Utente_Registrato {
    public static final int RUOLO_CLIENTE = 1;
    public static final int RUOLO_AMMINISTRATORE = 2;
    @Id
    @Column(length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false)
    private int ruolo;

    protected Utente_Registrato() {
    }

    protected Utente_Registrato(String email, String password, int ruolo) {
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
    }

    public int getRuolo() {
        return this.ruolo;
    }
    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean verificaPassword(String password) {
        return this.password != null && this.password.equals(password);
    }
}
