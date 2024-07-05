package JacopoDeMaio.GestioneEventi.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(
        String message,
        LocalDateTime timeStamp
) {
}
