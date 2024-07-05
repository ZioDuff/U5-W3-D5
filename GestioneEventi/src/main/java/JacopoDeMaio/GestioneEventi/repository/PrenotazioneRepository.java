package JacopoDeMaio.GestioneEventi.repository;

import JacopoDeMaio.GestioneEventi.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    List<Prenotazione> getPrenotazioniByEventiId(UUID eventiId);
}
