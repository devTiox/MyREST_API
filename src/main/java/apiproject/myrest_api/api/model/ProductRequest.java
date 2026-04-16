package apiproject.myrest_api.api.model;

import com.fasterxml.jackson.databind.JsonNode;

public class ProductRequest {

    private Long manufacturerId;
    private String name;
    private Double price;
    private JsonNode extraFields;

    public ProductRequest() {
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
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
}
