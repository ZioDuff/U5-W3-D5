package JacopoDeMaio.GestioneEventi.exceptions;
// exception creata apposta per gestire tutti gli errore di autenticazione
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message){
        super(message);
    }
}
