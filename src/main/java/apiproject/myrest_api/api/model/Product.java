package apiproject.myrest_api.api.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

// Mówi JPA, że ta klasa ma być mapowana na tabelę w bazie danych.
@Entity
public class Product {
    // Oznacza pole jako klucz główny encji.
    @Id
    // Każe bazie generować id automatycznie, zwykle jako auto-increment/identity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;
    // Informuje Hibernate, że to pole ma być zapisane jako typ JSON.
    @JdbcTypeCode(SqlTypes.JSON)
    // Wymusza kolumnę typu jsonb po stronie PostgreSQL.
    @Column(columnDefinition = "jsonb")
    private JsonNode extraFields;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Product(){

    }

    public Product(Long id, String name, Integer price, JsonNode extraFields) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.extraFields = extraFields;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public JsonNode getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(JsonNode extraFields) {
        this.extraFields = extraFields;
    }
}
