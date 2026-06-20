package PioBed.RentalService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Model filmu odbieranego z MovieService poprzez RestTemplate.
 *
 * Jest to klasyczny przykład "modelu generowanego z JSON-a" - temat zaznaczony przez
 * prowadzącego jako część zakresu kolokwium. RestTemplate.getForObject(url, Movie.class)
 * pobiera JSON z drugiego mikroserwisu i Jackson mapuje pola JSON-a na pola tego POJO.
 *
 * Uwaga: po stronie MovieService pole nazywa się "isAvailable". Jackson domyślnie nazywa
 * to pole jako "available" (zdejmuje przedrostek "is"), więc wymuszamy nazwę przez
 * @JsonProperty("isAvailable") na polu, getterze i setterze - dzięki temu serializacja
 * i deserializacja są deterministyczne niezależnie od konwencji bean-naming.
 *
 * Adnotacje @Schema dokumentują pola w Swagger UI (sekcja Schemas pod listą endpointów).
 */
@Schema(description = "Film odbierany z MovieService - reprezentacja po stronie wypożyczalni")
public class Movie {

    @Schema(description = "Identyfikator filmu", example = "1")
    private Long id;

    @Schema(description = "Tytuł filmu", example = "Incepcja")
    private String name;

    @Schema(description = "Kategoria filmu", example = "Sci-Fi")
    private String category;

    @Schema(description = "Rok produkcji", example = "2010")
    private int productionYear;

    @JsonProperty("isAvailable")
    @Schema(description = "Czy film jest dostępny do wypożyczenia", example = "true")
    private boolean isAvailable;

    public Movie() {
    }

    public Movie(Long id, String name, String category, int productionYear, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.productionYear = productionYear;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getProductionYear() {
        return productionYear;
    }

    @JsonProperty("isAvailable")
    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @JsonProperty("isAvailable")
    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
