package apiproject.myrest_api.api.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long manufacturerId;

    @Column(unique = true)
    private String name;
    private Double price;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private JsonNode extraFields;

    public Product() {
    }

    public Product(Long id, String name, Double price, Long manufacturerId, JsonNode extraFields) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.extraFields = extraFields;
        this.manufacturerId = manufacturerId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public JsonNode getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(JsonNode extraFields) {
        this.extraFields = extraFields;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
