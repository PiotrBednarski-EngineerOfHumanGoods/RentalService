package PioBed.RentalService.controller;

import PioBed.RentalService.model.Movie;
import PioBed.RentalService.service.RentalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return rentalService.getMovie(id);
    }

    @PostMapping("/{id}/return")
    public Movie returnMovie(@PathVariable Long id) {
        return rentalService.returnMovie(id);
    }

    @PostMapping("/{id}/rent")
    public Movie rentMovie(@PathVariable Long id) {
        return rentalService.rentMovie(id);
    }
}