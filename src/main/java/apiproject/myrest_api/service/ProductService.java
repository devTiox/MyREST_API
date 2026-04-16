package apiproject.myrest_api.service;

import apiproject.myrest_api.api.exception.ResourceNotFoundException;
import apiproject.myrest_api.api.model.Manufacturer;
import apiproject.myrest_api.api.model.Product;
import apiproject.myrest_api.api.model.ProductRequest;
import apiproject.myrest_api.repository.ManufacturerRepository;
import apiproject.myrest_api.repository.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ObjectMapper objectMapper;
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductService(ObjectMapper objectMapper, ProductRepository productRepository, ManufacturerRepository manufacturerRepository){
        this.objectMapper = objectMapper;
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }

    public Product createProduct(ProductRequest productRequest) {

        return productRepository.save(toProduct(productRequest));
    }

    public Optional<Manufacturer> getManufacturer(Long productId){
        return  productRepository.findById(productId).flatMap(
                product -> manufacturerRepository.findById(product.getManufacturerId()));
    }

    public Optional<Manufacturer> getManufacturer(String productName){
        return  productRepository.findByName(productName).flatMap(
                product -> manufacturerRepository.findById(product.getManufacturerId()));
    }

    public List<Product> createProducts(List<ProductRequest> productRequests) {
        List<Product> productEntities = productRequests.stream()
                .map(this::toProduct)
                .toList();
        return productRepository.saveAll(productEntities);
    }

    public List<Product> getProducts(List<Long> ids) {
        return productRepository.findAllById(ids);
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return updateProd(productRequest, productEntity);
    }

    public Product updateProduct(String name, ProductRequest productRequest){
        Product productEntity = productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return updateProd(productRequest, productEntity);
    }

    private Product updateProd(ProductRequest productRequest, Product productEntity) {
        Manufacturer manufacturerEntity = manufacturerRepository.findById(productRequest.getManufacturerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        productEntity.setManufacturerId(manufacturerEntity.getId());
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setExtraFields(productRequest.getExtraFields());

        return productRepository.save(productEntity);
    }

    public Product patchProduct(Long productId, Map<String, Object> updates) {
        Product productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return patchProduct(productEntity, updates);
    }

    public Product patchProduct(String productName, Map<String, Object> updates){
        Product productEntity = productRepository.findByName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return patchProduct(productEntity, updates);
    }

    public Product patchProduct(Product productEntity, Map<String, Object> updates){
        if (updates.containsKey("manufacturerId")) {
            productEntity.setManufacturerId((Long) updates.get("manufacturerId"));
        }
        if (updates.containsKey("name")) {
            productEntity.setName((String) updates.get("name"));
        }
        if (updates.containsKey("price")) {
            productEntity.setPrice((Double) updates.get("price"));
        }
        if (updates.containsKey("extraFields")) {
            JsonNode extraFieldsNode = objectMapper.valueToTree(updates.get("extraFields"));
            JsonNode currentExtraFields = productEntity.getExtraFields();

            if (currentExtraFields instanceof ObjectNode currentObjectNode
                    && extraFieldsNode instanceof ObjectNode extraFieldsObjectNode) {
                currentObjectNode.setAll(extraFieldsObjectNode);
                productEntity.setExtraFields(currentObjectNode);
            } else {
                productEntity.setExtraFields(extraFieldsNode);
            }
        }
        return productRepository.save(productEntity);
    }

    public List<Product> getManufacturerProducts(Long manufacturerId){
        return productRepository.findAllByManufacturerId(manufacturerId);
    }

    public List<Product> getManufacturerProducts(String manufacturerName) {
        return  manufacturerRepository.findByName(manufacturerName).map(
                manufacturer -> getManufacturerProducts(manufacturer.getId())).orElse(List.of());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProduct(String productName) {
        productRepository.deleteByName(productName);
    }

    private Product toProduct(ProductRequest productRequest) {
        Manufacturer manufacturerEntity = manufacturerRepository.findById(productRequest.getManufacturerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        Product productEntity = new Product();
        productEntity.setManufacturerId(manufacturerEntity.getId());
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setExtraFields(productRequest.getExtraFields());
        return productEntity;
    }
}
