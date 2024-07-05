package JacopoDeMaio.GestioneEventi.payloads;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PrenotazioneDTO(

        @NotNull(message = "l'id dell'utente è obbligatorio")
        UUID utenteId,
        @NotNull(message = "l'id dell'evento è obbligatorio")
        UUID eventoId
) {
}
