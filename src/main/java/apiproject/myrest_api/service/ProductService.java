package apiproject.myrest_api.service;

import apiproject.myrest_api.api.model.Manufacturer;
import apiproject.myrest_api.api.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ObjectMapper objectMapper;

    public ProductService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public Product getProduct(int id) {
        //TODO: Get from db
        return new Product();
    }

    public Product createProduct(Product product) {
        //TODO: Put to db

        return product;
    }

    public List<Product> createProducts(List<Product> products) {
        for(Product product : products){
            //TODO: Put all to db
        }
        return products;
    }

    public List<Product> getProducts(List<Integer> ids) {
        //TODO: Get all from db
        return List.of();
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product product = null;
        //TODO: get Product by id
        if (product != null && product.getId() == id) {
            product.setManufacturer(updatedProduct.getManufacturer());
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setExtraFields(updatedProduct.getExtraFields());
            return product;
        }
        return new Product();
    }

    public Product patchProduct(int id, Map<String, Object> updates) {
        Product product = null;
        //TODO: get Product by id
        if (product != null) {
            if (updates.containsKey("manufacturer")) {
                Manufacturer manufacturer = null;
                //TODO: taking manufacturer from db
                product.setManufacturer(manufacturer);

            }
            if (updates.containsKey("name")) {
                product.setName((String) updates.get("name"));
            }
            if(updates.containsKey("price")) {
                product.setPrice((Integer) updates.get("price"));
            }
            if (updates.containsKey("extraFields")) {
                JsonNode extraFieldsNode = objectMapper.valueToTree(updates.get("extraFields"));
                JsonNode currentExtraFields = product.getExtraFields();

                if (currentExtraFields instanceof ObjectNode currentObjectNode
                    && extraFieldsNode instanceof ObjectNode extraFieldsObjectNode) {
                    currentObjectNode.setAll(extraFieldsObjectNode);
                    product.setExtraFields(currentObjectNode);
                } else {
                    product.setExtraFields(extraFieldsNode);
                }
            }
            return product;
        }
        return new Product();
    }

    public Product deleteProduct(int id) {
        //TODO: delete from db
        return new Product();
    }
}
