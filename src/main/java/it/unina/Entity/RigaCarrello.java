package it.unina.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class RigaCarrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtaDesiderata;

    @ManyToOne (optional = false)
    private Carrello carrello;

    @ManyToOne (optional = false)
    private Prodotto prodotto;

    protected RigaCarrello() {
    }

    public void selezionaQuantità(int aQuantità) {
        throw new UnsupportedOperationException();
    }
}
