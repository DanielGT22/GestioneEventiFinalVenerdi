package daniel.grujic.GestioneEventi.services;

import daniel.grujic.GestioneEventi.entities.Luogo;
import daniel.grujic.GestioneEventi.exceptions.NotFoundException;
import daniel.grujic.GestioneEventi.repositories.LuogoRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class LuogoService {
    @Autowired
    private LuogoRepo luogoRepo;

    public Luogo save(Luogo luogo) {
        return luogoRepo.save(luogo);
    }

    public List<Luogo> getAllLuoghi() {
        return luogoRepo.findAll();
    }

    public Luogo getLuogoById(UUID id) {
        return luogoRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteLuogoById(UUID id) {
        luogoRepo.deleteById(id);
    }
}
