package it.unina.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (optional = false)
    @JoinColumn(name = "cliente_email")
    private Cliente cliente;
    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RigaCarrello> righe = new ArrayList<>();

    protected Carrello() {
    }

    public Carrello(Cliente cliente) {
        this.cliente = cliente;
    }

    public void aggiungiProdotto(Prodotto aProdotto) {
        throw new UnsupportedOperationException();
    }
}
