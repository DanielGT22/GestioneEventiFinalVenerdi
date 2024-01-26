package daniel.grujic.GestioneEventi.services;

import daniel.grujic.GestioneEventi.entities.Eventi;
import daniel.grujic.GestioneEventi.entities.Utente;
import daniel.grujic.GestioneEventi.exceptions.NotFoundException;
import daniel.grujic.GestioneEventi.payload.NewEventi;
import daniel.grujic.GestioneEventi.repositories.EventiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventiService {
    @Autowired
    UtenteService utenteService;

    @Autowired
    EventiRepo eventiRepo;

    public Eventi save(NewEventi body) {

        Eventi eventi = new Eventi();
        eventi.setTitolo(body.titolo());
        eventi.setDescrizione(body.descrizione());
        eventi.setData(body.date());
        eventi.setLuogo(body.luogo());
        return eventiRepo.save(eventi);
    }

    public Eventi findById(UUID id ){
        return eventiRepo.findById(id).orElseThrow(() -> new NotFoundException(id));


    }

    public void findByIdAndDelete(UUID id) {
        Eventi found = this.findById(id);
        eventiRepo.delete(found);
    }



    public List<Eventi> findByUtente(UUID utenteId){
        Utente utente = utenteService.findById(utenteId);
        return eventiRepo.findByUtente(utente);
    }
    public List<Eventi> getDispositivo() {
        return eventiRepo.findAll();
    }

}
