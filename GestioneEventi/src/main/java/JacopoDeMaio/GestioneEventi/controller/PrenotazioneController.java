package JacopoDeMaio.GestioneEventi.controller;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.entities.Prenotazione;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.payloads.PrenotazioneDTO;
import JacopoDeMaio.GestioneEventi.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;



    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public List<Prenotazione> getPrenotazioni(){

        return this.prenotazioneService.getAllPrenotazioni();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated PrenotazioneDTO payload, BindingResult validationResult){
        if (validationResult.hasErrors()){
            throw new BadRequestException(validationResult.getAllErrors());
        } else return prenotazioneService.savePrenotazione(payload);
    }



}
