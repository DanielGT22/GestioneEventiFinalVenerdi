package daniel.grujic.GestioneEventi.repositories;

import daniel.grujic.GestioneEventi.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepo extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByUsername(String username);
    Optional<Utente> findByEmail(String email);
}
