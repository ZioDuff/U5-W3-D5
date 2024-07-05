package JacopoDeMaio.GestioneEventi.payloads;

import JacopoDeMaio.GestioneEventi.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotEmpty(message = "il campo nome è obbligatorio ")
        @Size(min = 4,max = 20, message ="Il nome deve essere compreso tra i 4 e i 20 caratteri " )
        String nome,
        @NotEmpty(message = "il campo cognome è obbligatorio ")
        @Size(min = 4,max = 20, message ="Il cognome deve essere compreso tra i 4 e i 20 caratteri " )
        String cognome,
        @NotEmpty(message = "Il campo userName è obbligatorio ")
        @Size(min = 4,max = 20,message = "l'userName deve essere compreso tra i 4 e i 20 caratteri ")
        String username,
        @NotEmpty(message = "il campo email è obbligatorio ")
        @Email
        String email,
        @NotEmpty(message = "Il campo password è obbligatorio")
        @Size(min = 8,max = 16,message = "La password deve essere compresa tra gli 8 e i 16 caratteri")
        String password,
        @NotNull
        Role ruolo

) {
}
