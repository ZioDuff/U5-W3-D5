package JacopoDeMaio.GestioneEventi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {

    @Id
    @GeneratedValue
    @Column(name = "prenotazione_id")
    private UUID id;



    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;


    public Prenotazione( Utente utenti, Evento evento) {
        this.utente = utenti;
        this.evento = evento;
    }
}
