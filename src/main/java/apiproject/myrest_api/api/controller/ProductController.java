package apiproject.myrest_api.api.controller;

import apiproject.myrest_api.api.model.Manufacturer;
import apiproject.myrest_api.api.model.Product;
import apiproject.myrest_api.api.model.ProductRequest;
import apiproject.myrest_api.repository.ProductRepository;
import apiproject.myrest_api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository){
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long manufacturerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Double minPrice
    ){
        String normalizedName = name == null ? null : name.toLowerCase();
        return productRepository.searchProduct(id, manufacturerId, normalizedName, maxPrice, minPrice);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productRepository.getAll();
    }

    @GetMapping("/{productId}")
    public Optional<Product> getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

    @GetMapping("/by-name/{productName}")
    public Optional<Product> getProductByName(@PathVariable String productName){
        return productService.getProductByName(productName);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam List<Long> productIds){
        return productService.getProducts(productIds);
    }

    @GetMapping("/{productId}/manufacturer")
    public Optional<Manufacturer> getManufacturerByProductId(@PathVariable Long productId){
        return productService.getManufacturer(productId);
    }

    @GetMapping("/by-name/{productName}/manufacturer")
    public Optional<Manufacturer> getManufacturerByProductName(@PathVariable String productName){
        return productService.getManufacturer(productName);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PostMapping("/batch")
    public List<Product> createProducts(@RequestBody List<ProductRequest> productRequests){
        return productService.createProducts(productRequests);
    }

    @PutMapping("/{productId}")
    public Product updateProductById(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @PutMapping("/by-name/{productName}")
    public Product updateProductByName(@PathVariable String productName, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productName, productRequest);
    }

    @PatchMapping("/{productId}")
    public Product patchProductById(@PathVariable Long productId, @RequestBody Map<String, Object> updates) {
        return productService.patchProduct(productId, updates);
    }

    @PatchMapping("/by-name/{productName}")
    public Product patchProductByName(@PathVariable String productName, @RequestBody Map<String, Object> updates) {
        return productService.patchProduct(productName, updates);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    @DeleteMapping("/by-name/{productName}")
    public void deleteProductByName(@PathVariable String productName) {
        productService.deleteProduct(productName);
    }

}
