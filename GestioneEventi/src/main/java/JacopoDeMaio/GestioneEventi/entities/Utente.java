package JacopoDeMaio.GestioneEventi.entities;

import JacopoDeMaio.GestioneEventi.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Utenti")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"password", "authorities", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue

    private UUID id;

    private String nome;

    private String cognome;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role ruolo;

    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    private List<Prenotazione> prenotazioni;

    public Utente(String nome, String cognome, String username, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ruolo = Role.UTENTE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
