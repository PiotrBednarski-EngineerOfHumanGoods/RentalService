package PioBed.RentalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punkt startowy mikroserwisu RentalService (wypożyczalnia).
 *
 * Aplikacja działa na porcie 8082 (ustawienie w application.properties).
 * MovieService musi być uruchomiony na 8081 zanim RentalService zacznie obsługiwać
 * requesty - inaczej każde wywołanie zakończy się błędem 504 (ConnectException
 * łapany przez GlobalExceptionHandler).
 */
@SpringBootApplication
public class RentalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentalServiceApplication.class, args);
    }
}
