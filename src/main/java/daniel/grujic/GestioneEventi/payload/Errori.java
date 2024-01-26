package daniel.grujic.GestioneEventi.payload;

import java.time.LocalDateTime;
import java.util.Date;

public record Errori(String message, LocalDateTime timestamp) {
    public Errori(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
