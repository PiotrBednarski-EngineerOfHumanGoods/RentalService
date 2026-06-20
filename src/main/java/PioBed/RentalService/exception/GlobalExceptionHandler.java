package PioBed.RentalService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;

/**
 * Praca domowa ze zjazdu 6.
 *
 * Treść zadania:
 *   "Do serwisu RentalService dodaj obsługę statusów zwróconych przez MovieService.
 *    - Jeżeli MovieService zwróci HTTP status 404, niech RentalService też zwróci 404,
 *    - Jeżeli MovieService zwróci HTTP status 400, niech RentalService też zwróci 400,
 *    - Jeżeli MovieService zwróci HTTP status 500, niech RentalService zwróci 502,
 *    - Jeżeli MovieService zwróci ConnectException, niech RentalService zwróci 504."
 *
 * Adnotacja @RestControllerAdvice sprawia że metody @ExceptionHandler działają
 * dla wszystkich kontrolerów REST w aplikacji - łapią wyjątki rzucane przez RestTemplate.
 *
 * Klasy wyjątków:
 *   - HttpClientErrorException.NotFound     - rzucone przy odpowiedzi 4xx z drugiego serwisu (tu: 404).
 *   - HttpClientErrorException.BadRequest   - rzucone przy 400.
 *   - HttpServerErrorException.InternalServerError - rzucone przy 500.
 *   - ResourceAccessException               - rzucone przy problemach z połączeniem
 *                                              (np. ConnectException gdy MovieService nie odpowiada).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<String> handleNotFound(HttpClientErrorException.NotFound exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("MovieService returned 404 - movie not found");
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(HttpClientErrorException.BadRequest exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("MovieService returned 400 - bad request");
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<String> handleInternalServerError(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("MovieService returned 500 - bad gateway");
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> handleConnectionException(ResourceAccessException exception) {
        // Sprawdzamy korzeń przyczyny - ConnectException oznacza że nie udało się
        // połączyć z drugim serwisem (np. MovieService nie jest uruchomiony).
        if (exception.getCause() instanceof ConnectException) {
            return ResponseEntity
                    .status(HttpStatus.GATEWAY_TIMEOUT)
                    .body("Cannot connect to MovieService");
        }

        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body("MovieService is unavailable");
    }
}
