package JacopoDeMaio.GestioneEventi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record EventoDTO(
        @NotEmpty(message = "Il campo titolo è obbligatorio")
        @Size(min = 5,max = 30, message = "il titolo deve essere compreso tra i 5 e i 30 caratteri")
        String titolo,
        @Size(min = 10,max = 50,message = "la descrizione deve essere compresa tra i 10 e i 50 caratteri")
        String descrizione,
        @NotNull
        LocalDate dataEvento,
        @NotEmpty
        @Size(min = 3,max = 20,message = "il nome del luogo deve essere compreso tra i 3 caratteri e i 20 caratteri")
        String luogo,
        @NotNull
        int maxPartecipanti,
        @NotNull
        UUID utenteId
) {
}
