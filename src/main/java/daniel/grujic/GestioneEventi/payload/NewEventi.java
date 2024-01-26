package daniel.grujic.GestioneEventi.payload;

import daniel.grujic.GestioneEventi.entities.Luogo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventi(
        @NotNull(message = "Inserisci il titolo del Evento")
        String titolo,
        @NotNull(message = "Inserisci la descrizione del evento")
        String descrizione,
        @NotNull(message = "Inserisci la data del evento")
        LocalDate date,

        @NotNull(message = "Inserisci il luogo del evento")
        Luogo luogo

) {}