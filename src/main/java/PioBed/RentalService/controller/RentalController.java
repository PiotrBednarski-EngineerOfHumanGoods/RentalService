package PioBed.RentalService.controller;

import PioBed.RentalService.model.Movie;
import PioBed.RentalService.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler REST dla zasobu Rental.
 *
 * Ten serwis jest "wypożyczalnią" - sam nie trzyma filmów, tylko deleguje pracę
 * do MovieService po REST (RestTemplate). To realizacja ćwiczeń ze zjazdu 6:
 *
 *   Ćw.3: "Stwórz nowy RestController oraz Service dla dwóch metod:
 *          - getMovie(Long id) -> zapytanie REST do MovieService po endpoint
 *            szukający Movie po ID, zwraca film użytkownikowi.
 *          - returnMovie(Long id) -> zapytanie REST do MovieService wołające
 *            endpoint z pracy domowej (PATCH /movies/{id}/available =>
 *            ustawia is_available na true)."
 *   Ćw.4: "rentMovie(Long id) -> zapytanie REST do MovieService wołające endpoint
 *          ustawiający is_available na false."
 *
 * Adnotacja @Tag grupuje endpointy w Swagger UI pod nazwą "Wypożyczenia".
 */
@RestController
@RequestMapping("/rentals")
@Tag(name = "Wypożyczenia", description = "Endpointy do obsługi wypożyczeń i zwrotów filmów")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pobierz film po ID")
    public Movie getMovie(@PathVariable Long id) {
        return rentalService.getMovie(id);
    }

    @PostMapping("/{id}/return")
    @Operation(summary = "Zwróć film")
    public Movie returnMovie(@PathVariable Long id) {
        return rentalService.returnMovie(id);
    }

    @PostMapping("/{id}/rent")
    @Operation(summary = "Wypożycz film")
    public Movie rentMovie(@PathVariable Long id) {
        return rentalService.rentMovie(id);
    }
}
