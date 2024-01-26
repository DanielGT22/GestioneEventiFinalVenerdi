package daniel.grujic.GestioneEventi.services;

import daniel.grujic.GestioneEventi.entities.Tipo;
import daniel.grujic.GestioneEventi.entities.Utente;
import daniel.grujic.GestioneEventi.exceptions.BadRequestException;
import daniel.grujic.GestioneEventi.exceptions.UnauthorizedException;
import daniel.grujic.GestioneEventi.payload.NewUtenteDTO;
import daniel.grujic.GestioneEventi.payload.UtenteLogin;
import daniel.grujic.GestioneEventi.repositories.UtenteRepo;
import daniel.grujic.GestioneEventi.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepo utenteRepo;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UtenteLogin body) {
        Utente utente = utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public Utente save(NewUtenteDTO body) {

        utenteRepo.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });

        Utente newUtente = new Utente();
        newUtente.setNome(body.nome());
        newUtente.setCognome(body.cognome());
        newUtente.setUsername(body.username());
        newUtente.setEmail(body.email());
        newUtente.setPassword(bcrypt.encode(body.password()));
        newUtente.setTipo(Tipo.UTENTE_NORMALE);
        return utenteRepo.save(newUtente);
    }
}
