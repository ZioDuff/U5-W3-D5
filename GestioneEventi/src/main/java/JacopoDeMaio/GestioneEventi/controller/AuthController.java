package JacopoDeMaio.GestioneEventi.controller;

import JacopoDeMaio.GestioneEventi.entities.Utente;
import JacopoDeMaio.GestioneEventi.exceptions.BadRequestException;
import JacopoDeMaio.GestioneEventi.payloads.NewUtenteResponseDTO;
import JacopoDeMaio.GestioneEventi.payloads.UtenteDTO;
import JacopoDeMaio.GestioneEventi.payloads.UtenteLoginDTO;
import JacopoDeMaio.GestioneEventi.payloads.UtenteLoginResponseDTO;
import JacopoDeMaio.GestioneEventi.services.AuthService;
import JacopoDeMaio.GestioneEventi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;



    @PostMapping("/login")
    public UtenteLoginResponseDTO login (@RequestBody UtenteLoginDTO payload){
        return  new UtenteLoginResponseDTO(authService.authenticateDipendentiAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteResponseDTO saveUtente(@RequestBody @Validated UtenteDTO payload, BindingResult validationResult){
        if (validationResult.hasErrors()){
            throw  new BadRequestException(validationResult.getAllErrors());
        }else return new NewUtenteResponseDTO(utenteService.saveDipendente(payload).getId());
    }
}
