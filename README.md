# RentalService

Mikroserwis „wypożyczalnia" — projekt z przedmiotu Java Zaawansowana (PJATK).
Aplikacja deleguje wszystkie operacje na filmach do MovieService przez REST (RestTemplate).

## Uruchomienie

```bash
mvn clean spring-boot:run
```

Aplikacja nasłuchuje na **porcie 8082**.

**Wymagane:** MovieService musi być uruchomiony na porcie 8081 zanim
RentalService zacznie obsługiwać żądania (inaczej każde wywołanie zwraca 504
przez `ConnectException`).

## Swagger / OpenAPI

- Swagger UI: <http://localhost:8082/swagger-ui.html>
- Specyfikacja JSON: <http://localhost:8082/v3/api-docs>

## Endpointy (grupa „Wypożyczenia" w Swagger UI)

```
GET   /rentals/{id}             # Pobiera film z MovieService po ID
POST  /rentals/{id}/return      # Zwrot - PATCH /movies/{id}/available na MovieService
POST  /rentals/{id}/rent        # Wypożyczenie - PATCH /movies/{id}/rent na MovieService
```

## Ściągawka — gdzie szukać czego w kodzie

| Temat | Plik |
|---|---|
| **Request HTTP do innego serwisu (RestTemplate)** | `service/RentalService.java` (`getMovie`, `returnMovie`, `rentMovie`) |
| **Model generowany z JSON-a (POJO + Jackson)** | `model/Movie.java` |
| **@JsonProperty** | `model/Movie.java` (pole `isAvailable`) |
| **Bean RestTemplate w @Configuration** | `AppConfig.java` |
| **@RestControllerAdvice** (obsługa kodów błędów innego serwisu) | `exception/GlobalExceptionHandler.java` |
| **Mapowanie 404 -> 404, 400 -> 400, 500 -> 502, ConnectException -> 504** | `exception/GlobalExceptionHandler.java` |
| **Swagger @Tag + @Operation** | `controller/RentalController.java` |
| **Swagger @Schema** | `model/Movie.java` |

## Mapa zjazdów

- **Zjazd 6 ćw. 1–4** — utworzenie projektu, port 8082, model Movie, bean RestTemplate,
  trzy endpointy (`getMovie`, `returnMovie`, `rentMovie`)
- **Zjazd 6 PD** — `GlobalExceptionHandler` mapujący statusy MovieService na statusy RentalService
- **Zjazd 7 ćw. 1+2** — Swagger dla endpointów i modeli
