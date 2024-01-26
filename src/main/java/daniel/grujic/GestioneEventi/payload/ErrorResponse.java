package daniel.grujic.GestioneEventi.payload;

import java.util.Date;
import java.util.List;

public record ErrorResponse (
    String message,
    Date timestamp,
    List<String> errorsList) {
    }
