package JacopoDeMaio.GestioneEventi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Eventi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Evento  {

    @Id
    @GeneratedValue
    private UUID id;

    private String titolo;

    private String descrizione;

    @Column(name = "data_evento")
    private LocalDate dataEvento;

    private String luogo;

    @Column(name = "numero_max_partecipanti")
    private int maxPartecipanti;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente utente;

    public Evento(String titolo, String descrizione, LocalDate dataEvento, String luogo, int maxPartecipanti, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataEvento = dataEvento;
        this.luogo = luogo;
        this.maxPartecipanti = maxPartecipanti;
        this.utente = utente;
    }
}
