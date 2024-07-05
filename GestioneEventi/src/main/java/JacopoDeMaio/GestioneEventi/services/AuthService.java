package JacopoDeMaio.GestioneEventi.services;



import JacopoDeMaio.GestioneEventi.entities.Utente;
import JacopoDeMaio.GestioneEventi.exceptions.UnauthorizedException;
import JacopoDeMaio.GestioneEventi.payloads.UtenteLoginDTO;
import JacopoDeMaio.GestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;


    public String authenticateDipendentiAndGenerateToken(UtenteLoginDTO payload) {

        Utente utente = this.utenteService.findByEmail(payload.email());

        if (bcrypt.matches(payload.password(),utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("credenziali non corrette");
        }
    }
}
