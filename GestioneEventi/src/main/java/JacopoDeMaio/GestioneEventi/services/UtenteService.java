package JacopoDeMaio.GestioneEventi.services;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.entities.Utente;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.exceptions.NotFoundException;
import JacopoDeMaio.GestioneEventi.payloads.UtenteDTO;
import JacopoDeMaio.GestioneEventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;




    //    metodo per salvare l'utente
    public Utente saveDipendente(UtenteDTO payload){
//        controllo per verificare se è gia esistente un email
        utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {
            throw  new BadRequestException("Attenzione! l'email: "+ payload.email() + " è gia in uso");
        });
//        controllo per verificare se l'userName è gia presente
        utenteRepository.findByUsername(payload.username()).ifPresent(utente -> {
            throw  new BadRequestException("Attenzione! l'username: "+ payload.username() + " è gia in uso");
        });

        Utente utente = new Utente(payload.nome(), payload.cognome(), payload.username(), payload.email(), bcrypt.encode(payload.password()));

        Utente saved = utenteRepository.save(utente);



        return saved;
    }


    public Page<Utente> getUtentiList(int page, int size, String sortedBy){
        if (size > 20) size= 20;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortedBy));
        return utenteRepository.findAll(pageable);
    }


    public Utente findUtenteById(UUID utenteId){
        Utente found = utenteRepository.findById(utenteId).orElseThrow(()-> new NotFoundException(utenteId));
        return found;
    }


    public Utente findUtenteByIdAndUpdate(UUID utenteId,Utente payload){
        Utente found = utenteRepository.findById(utenteId).orElseThrow(()-> new NotFoundException(utenteId));
        found.setUsername(payload.getUsername());
        found.setNome(payload.getNome());
        found.setCognome(payload.getCognome());
        found.setEmail(payload.getEmail());
        found.setPassword(payload.getPassword());
        return utenteRepository.save(found);
    }

    //    metodo per eliminare un dipendente dal db
    public void findUtenteByIdAndDelete(UUID dipendenteId){
        Utente found = utenteRepository.findById(dipendenteId).orElseThrow(()-> new NotFoundException(dipendenteId));
        utenteRepository.delete(found);
    }

    public Utente findByEmail(String email){
        return  utenteRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("l'utente con email: " + email + " non è stato trovato"));
    }

}
