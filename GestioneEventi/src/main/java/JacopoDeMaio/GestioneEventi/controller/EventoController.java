package JacopoDeMaio.GestioneEventi.controller;

import JacopoDeMaio.GestioneEventi.entities.Evento;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.payloads.EventoDTO;
import JacopoDeMaio.GestioneEventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @GetMapping
    public Page<Evento> gellEventi(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String sortedBy){
        return eventoService.getEventiList(page,size,sortedBy);
    }

    @PostMapping("/newEvento")
    @PreAuthorize("hasAuthority('ORGANIZZATORE') || hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento saveEvento(@RequestBody @Validated EventoDTO payload, BindingResult validationResult){
        if (validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return  eventoService.saveEvento(payload);
    }

    @GetMapping("/{eventoId}")
    public Evento findDipendenteById(@PathVariable UUID dipendenteId){
        return eventoService.findById(dipendenteId);
    }

    @PutMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento findDipendenteByIdAndUpdate(@PathVariable UUID dipendenteId,@RequestBody EventoDTO payload){
        return eventoService.findByIdAndUpdate(dipendenteId,payload);
    }

    @DeleteMapping("/{dipendenteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findDipendenteByIdAndDelete(@PathVariable UUID eventoId){
        eventoService.findByIdAndDelete(eventoId);
    }



}
