package daniel.grujic.GestioneEventi.repositories;

import daniel.grujic.GestioneEventi.entities.Eventi;
import daniel.grujic.GestioneEventi.entities.Luogo;
import daniel.grujic.GestioneEventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LuogoRepo extends JpaRepository<Luogo, UUID> {


}
