package daniel.grujic.GestioneEventi.services;

import daniel.grujic.GestioneEventi.entities.Utente;
import daniel.grujic.GestioneEventi.exceptions.BadRequestException;
import daniel.grujic.GestioneEventi.exceptions.NotFoundException;
import daniel.grujic.GestioneEventi.payload.NewUtenteDTO;
import daniel.grujic.GestioneEventi.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UtenteService {
    @Autowired
    private UtenteRepo utenteRepo;

    public Utente save(NewUtenteDTO body){
        utenteRepo.findByUsername(body.username()).ifPresent( user -> {
            throw new BadRequestException("Il username " +  body.username() + "è già associato ad un altro utente");
        });
        utenteRepo.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("La mail  " +  body.email() + "è già associato ad un altro utente");
        });
        Utente newUtente = new Utente();
        newUtente.setNome(body.nome());
        newUtente.setCognome(body.cognome());
        newUtente.setEmail(body.email());
        newUtente.setUsername(body.username());
        newUtente.setPassword(body.password());
        return utenteRepo.save(newUtente);

    }

    public Page<Utente> getUtenti(int pagina, int elementi, String sortBy) {
        Pageable pageable = PageRequest.of(pagina, elementi, Sort.by(sortBy));
        return utenteRepo.findAll(pageable);
    }

    public Utente findById(UUID id ){
        return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public void findByIdAndDelete(UUID id) {
        Utente trovato = this.findById(id);
        utenteRepo.delete(trovato);
    }

    public Utente findByIdAndUpdate(UUID id, Utente body) {
        Utente trovato = this.findById(id);
        trovato.setNome(body.getNome());
        trovato.setCognome(body.getCognome());
        trovato.setEmail(body.getEmail());
        trovato.setUsername(body.getUsername());
        return utenteRepo.save(trovato);
    }
    public Utente findByEmail(String email) throws NotFoundException {
        return utenteRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }
}
