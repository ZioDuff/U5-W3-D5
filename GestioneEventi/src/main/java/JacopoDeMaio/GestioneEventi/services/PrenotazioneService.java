package JacopoDeMaio.GestioneEventi.services;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.entities.Prenotazione;
import JacopoDeMaio.GestioneEventi.entities.Utente;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.payloads.PrenotazioneDTO;
import JacopoDeMaio.GestioneEventi.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EventoService eventoService;



    public List<Prenotazione> getAllPrenotazioni(){return this.prenotazioneRepository.findAll();}

    public Prenotazione savePrenotazione(PrenotazioneDTO payload){
        Utente utente = utenteService.findUtenteById(payload.utenteId());
        Evento evento = eventoService.findById(payload.eventoId());

        if (prenotazioneRepository.getPrenotazioniByEventiId(payload.eventoId()).size() >= evento.getMaxPartecipanti()){

            throw new BadRequestException("L'evento " + evento.getTitolo() + "ha raggiunto la capienza massima");

        }else {
            Prenotazione prenotazione = new Prenotazione(utente,evento);
              return prenotazioneRepository.save(prenotazione);
        }


    }


}
