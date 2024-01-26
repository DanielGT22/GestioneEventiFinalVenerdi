package daniel.grujic.GestioneEventi.payload;

import java.time.LocalDateTime;

public record Errori(String message, LocalDateTime timestamp) {
}
