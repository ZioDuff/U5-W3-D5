package JacopoDeMaio.GestioneEventi.services;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.entities.Utente;
import JacopoDeMaio.GestioneEventi.enums.Role;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.exceptions.NotFoundException;
import JacopoDeMaio.GestioneEventi.payloads.EventoDTO;
import JacopoDeMaio.GestioneEventi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;


    public Evento saveEvento(EventoDTO payload){

        eventoRepository.findUtenteById(payload.utenteId()).ifPresent(evento -> {
            throw new BadRequestException("Attenzione non è presente nessun organizzatore per questo evento");

        });

        Utente found = utenteService.findUtenteById(payload.utenteId());


        Evento evento = new Evento(payload.titolo(), payload.descrizione(), payload.dataEvento(), payload.luogo(), payload.maxPartecipanti(), found );

        eventoRepository.save(evento);

        return evento;

    }

    public Page<Evento> getEventiList(int page, int size, String sortedBy){
        if (size > 20) size= 20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortedBy));
        return eventoRepository.findAll(pageable);
    }


    public Evento findById(UUID eventoId){
        return eventoRepository.findById(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
    }


    public Evento findByIdAndUpdate(UUID eventoId, EventoDTO payload){
        Evento found = eventoRepository.findById(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
        found.setTitolo(payload.titolo());
        found.setDescrizione(payload.descrizione());
        found.setDataEvento(payload.dataEvento());
        found.setLuogo(payload.luogo());
        found.setMaxPartecipanti(payload.maxPartecipanti());
        return eventoRepository.save(found);
    }

    public void findByIdAndDelete(UUID eventoId){
        Evento found = eventoRepository.findById(eventoId).orElseThrow(()-> new NotFoundException(eventoId));
        eventoRepository.delete(found);
    }
}
