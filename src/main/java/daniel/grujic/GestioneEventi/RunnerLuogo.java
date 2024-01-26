package daniel.grujic.GestioneEventi;

import com.github.javafaker.Faker;
import daniel.grujic.GestioneEventi.entities.Luogo;
import daniel.grujic.GestioneEventi.repositories.LuogoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RunnerLuogo implements CommandLineRunner {
    @Autowired
    private LuogoRepo luogoRepo;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            Luogo luogo = new Luogo();
            luogo.setEdificio(faker.address().fullAddress());
            luogo.setCitta(faker.address().cityName());
            luogo.setPosti( random.nextInt(400));
            luogo.setLibero(true);

            luogoRepo.save(luogo);
        }
    }
}
