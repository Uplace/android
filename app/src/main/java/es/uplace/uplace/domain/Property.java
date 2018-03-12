package es.uplace.uplace.domain;

import android.location.Location;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import es.uplace.uplace.domain.enumeration.TransactionType;

public class Property {

    private String title;
    private Double price;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private String propertyType;
    private String description;
    private TransactionType transaction;
    private String reference;
    private Double priceSell;
    private Double priceRent;
    private Double priceTransfer;
    private Integer yearConstruction;
    private Boolean newCreation;
    private Boolean visible;
    private Integer surface;
    private Location location;
    private Set<Photo> photos = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getTransaction() {
        return transaction;
    }

    public String getReference() {
        return reference;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public Double getPriceRent() {
        return priceRent;
    }

    public Double getPriceTransfer() {
        return priceTransfer;
    }

    public Integer getYearConstruction() {
        return yearConstruction;
    }

    public Boolean getNewCreation() {
        return newCreation;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Integer getSurface() {
        return surface;
    }

    public Location getLocation() {
        return location;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "Property{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", created=" + created +
                ", updated=" + updated +
                ", propertyType='" + propertyType + '\'' +
                ", description='" + description + '\'' +
                ", transaction=" + transaction +
                ", reference='" + reference + '\'' +
                ", priceSell=" + priceSell +
                ", priceRent=" + priceRent +
                ", priceTransfer=" + priceTransfer +
                ", yearConstruction=" + yearConstruction +
                ", newCreation=" + newCreation +
                ", visible=" + visible +
                ", surface=" + surface +
                ", location=" + location +
                ", photos=" + photos +
                '}';
    }
}
