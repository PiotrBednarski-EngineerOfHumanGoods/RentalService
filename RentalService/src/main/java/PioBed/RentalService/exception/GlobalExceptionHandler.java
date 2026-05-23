package PioBed.RentalService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;

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