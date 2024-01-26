package daniel.grujic.GestioneEventi.controllers;

import daniel.grujic.GestioneEventi.entities.Utente;
import daniel.grujic.GestioneEventi.exceptions.BadRequestException;
import daniel.grujic.GestioneEventi.payload.NewUtenteDTO;
import daniel.grujic.GestioneEventi.payload.NewUtenteResponse;
import daniel.grujic.GestioneEventi.payload.UtenteLogin;
import daniel.grujic.GestioneEventi.payload.UtenteLoginResponse;
import daniel.grujic.GestioneEventi.services.AuthService;
import daniel.grujic.GestioneEventi.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {

        @Autowired
        AuthService authService;
        @Autowired
        UtenteService utenteService;

        @PostMapping("/login")
        public UtenteLoginResponse login(@RequestBody UtenteLogin body) {
            String accessToken = authService.authenticateUser(body);
            return new UtenteLoginResponse(accessToken);
        }

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public NewUtenteResponse createUser(@RequestBody @Validated NewUtenteDTO newUtentePayload, BindingResult validation)  {
            System.out.println(validation);
            if (validation.hasErrors()) {
                System.out.println(validation.getAllErrors());
                throw new BadRequestException("Ci sono errori nel payload!");
            } else {
                Utente newUtente = utenteService.save(newUtentePayload);

                return new NewUtenteResponse(newUtente.getId());
            }
        }
}
