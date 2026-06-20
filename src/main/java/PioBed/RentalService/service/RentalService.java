package PioBed.RentalService.service;

import PioBed.RentalService.model.Movie;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Logika biznesowa wypożyczalni.
 *
 * Klasa pokazuje "request HTTP do innego serwisu" - temat zaznaczony przez prowadzącego
 * jako część zakresu kolokwium. RestTemplate to klient HTTP dostarczany przez Springa,
 * obsługujący najczęstsze metody REST: getForObject, postForObject, exchange (uniwersalne),
 * delete itd.
 *
 * Bean RestTemplate jest definiowany w AppConfig - tutaj jest wstrzykiwany przez konstruktor.
 *
 * MovieService jest dostępny pod localhost:8081 (port ustawiony po stronie MovieService
 * w application.yml).
 */
@Service
public class RentalService {

    private static final String MOVIE_SERVICE_BASE_URL = "http://localhost:8081/movies";

    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Pobiera film z MovieService przez GET /movies/{id}.
     *
     * RestTemplate odbiera JSON i Jackson mapuje go na obiekt klasy Movie
     * (przykład "model generowany z JSON-a").
     */
    public Movie getMovie(Long id) {
        return restTemplate.getForObject(
                MOVIE_SERVICE_BASE_URL + "/" + id,
                Movie.class
        );
    }

    /**
     * Zwraca film - woła PATCH /movies/{id}/available na MovieService,
     * który ustawia isAvailable = true. Po zmianie statusu pobieramy aktualny film.
     */
    public Movie returnMovie(Long id) {
        restTemplate.exchange(
                MOVIE_SERVICE_BASE_URL + "/" + id + "/available",
                HttpMethod.PATCH,
                null,
                Void.class
        );

        return getMovie(id);
    }

    /**
     * Wypożycza film - woła PATCH /movies/{id}/rent na MovieService,
     * który ustawia isAvailable = false. Po zmianie statusu pobieramy aktualny film.
     */
    public Movie rentMovie(Long id) {
        restTemplate.exchange(
                MOVIE_SERVICE_BASE_URL + "/" + id + "/rent",
                HttpMethod.PATCH,
                null,
                Void.class
        );

        return getMovie(id);
    }
}
