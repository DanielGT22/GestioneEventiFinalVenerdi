package daniel.grujic.GestioneEventi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "evento")
public class Eventi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int posti;
    private boolean libero;

    @OneToMany
    @JoinColumn(name = "utenteId")
    private Utente utente;
}