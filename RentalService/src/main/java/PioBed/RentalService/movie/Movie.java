package PioBed.RentalService.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    private Long id;
    private String name;
    private String category;
    private int productionYear;

    @JsonProperty("isAvailable")
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