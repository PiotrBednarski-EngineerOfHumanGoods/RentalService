package PioBed.RentalService.service;

import PioBed.RentalService.model.Movie;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RentalService {

    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(Long id) {
        return restTemplate.getForObject(
                "http://localhost:8081/movies/" + id,
                Movie.class
        );
    }

    public Movie returnMovie(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/movies/" + id + "/available",
                HttpMethod.PATCH,
                null,
                Void.class
        );

        return getMovie(id);
    }

    public Movie rentMovie(Long id) {
        restTemplate.exchange(
                "http://localhost:8081/movies/" + id + "/rent",
                HttpMethod.PATCH,
                null,
                Void.class
        );

        return getMovie(id);
    }
}