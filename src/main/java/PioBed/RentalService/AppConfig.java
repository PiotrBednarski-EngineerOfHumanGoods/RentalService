package PioBed.RentalService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Klasa konfiguracyjna - rejestruje RestTemplate jako bean.
 *
 * Treść zadania ze zjazdu 6 (ćwiczenie 2):
 *   "Dodaj bean dla obiektu RestTemplate."
 *
 * RestTemplate to klient HTTP używany przez RentalService do wywoływania endpointów
 * MovieService. Dzięki @Bean Spring rejestruje obiekt w kontekście, a RentalService
 * dostaje go wstrzykniętego przez konstruktor.
 *
 * To również przykład różnicy między @Component a @Bean - RestTemplate to klasa
 * spoza naszego projektu, nie możemy oznaczyć jej @Component, więc rejestrujemy
 * ją ręcznie w klasie @Configuration.
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
