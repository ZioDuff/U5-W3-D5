package JacopoDeMaio.GestioneEventi.controller;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.payloads.EventoDTO;
import JacopoDeMaio.GestioneEventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @PostMapping("/newEvento")
    @PreAuthorize("hasAuthority('ORGANIZZATORE') || hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento saveEvento(@RequestBody @Validated EventoDTO payload, BindingResult validationResult){
        if (validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return  eventoService.saveEvento(payload);
    }


}
