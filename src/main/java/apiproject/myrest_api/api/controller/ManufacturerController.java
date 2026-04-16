package apiproject.myrest_api.api.controller;

import apiproject.myrest_api.api.model.Manufacturer;
import apiproject.myrest_api.api.model.ManufacturerRequest;
import apiproject.myrest_api.api.model.Product;
import apiproject.myrest_api.repository.ManufacturerRepository;
import apiproject.myrest_api.service.ManufacturerService;
import apiproject.myrest_api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;
    private final ProductService productService;
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerController(ManufacturerService manufacturerService, ProductService productService, ManufacturerRepository manufacturerRepository){
        this.manufacturerService = manufacturerService;
        this.productService = productService;
        this.manufacturerRepository = manufacturerRepository;
    }

    @GetMapping("/search")
    public List<Manufacturer> searchManufacturer(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone
    ) {
        String normalizedName = name == null ? null : name.toLowerCase();
        String normalizedAddress = address == null ? null : address.toLowerCase();
        String normalizedEmail = email == null ? null : email.toLowerCase();
        return manufacturerRepository.searchManufacturer(id, normalizedName, normalizedAddress, normalizedEmail, phone);
    }

    @GetMapping("/{manufacturerId}")
    public Manufacturer getManufacturerById(@PathVariable Long manufacturerId){
        return manufacturerService.getManufacturer(manufacturerId);
    }

    @GetMapping("/by-name/{manufacturerName}")
    public Manufacturer getManufacturerByName(@PathVariable String manufacturerName){
        return manufacturerService.getManufacturer(manufacturerName);
    }

    @GetMapping
    public List<Manufacturer> getManufacturers(@RequestParam List<Long> manufacturerIds){
        return manufacturerService.getManufacturers(manufacturerIds);
    }

    @GetMapping("/{manufacturerId}/products")
    public List<Product> getManufacturerProductsById(@PathVariable Long manufacturerId){
        return productService.getManufacturerProducts(manufacturerId);
    }

    @GetMapping("/by-name/{manufacturerName}/products")
    public List<Product> getManufacturerProductsByName(@PathVariable String manufacturerName){
        return productService.getManufacturerProducts(manufacturerName);
    }

    @PostMapping
    public Manufacturer createManufacturer(@RequestBody ManufacturerRequest manufacturerRequest){
        return manufacturerService.createManufacturer(manufacturerRequest);
    }

    @PostMapping("/batch")
    public List<Manufacturer> createManufacturers(@RequestBody List<ManufacturerRequest> manufacturerRequests){
        return manufacturerService.createManufacturers(manufacturerRequests);
    }

    @PutMapping("/{manufacturerId}")
    public Manufacturer updateManufacturerById(@PathVariable Long manufacturerId, @RequestBody ManufacturerRequest manufacturerRequest) {
        return manufacturerService.updateManufacturer(manufacturerId, manufacturerRequest);
    }

    @PutMapping("/by-name/{manufacturerName}")
    public Manufacturer updateManufacturerByName(@PathVariable String manufacturerName, @RequestBody ManufacturerRequest manufacturerRequest) {
        return manufacturerService.updateManufacturer(manufacturerName, manufacturerRequest);
    }

    @PatchMapping("/{manufacturerId}")
    public Manufacturer patchManufacturerById(@PathVariable Long manufacturerId, @RequestBody Map<String, Object> updates) {
        return manufacturerService.patchManufacturer(manufacturerId, updates);
    }

    @PatchMapping("/by-name/{manufacturerName}")
    public Manufacturer patchManufacturerByName(@PathVariable String manufacturerName, @RequestBody Map<String, Object> updates) {
        return manufacturerService.patchManufacturer(manufacturerName, updates);
    }

    @DeleteMapping("/{manufacturerId}")
    public Manufacturer deleteManufacturerById(@PathVariable Long manufacturerId) {
        return manufacturerService.deleteManufacturer(manufacturerId);
    }

    @DeleteMapping("/by-name/{manufacturerName}")
    public Manufacturer deleteManufacturerByName(@PathVariable String manufacturerName) {
        return manufacturerService.deleteManufacturer(manufacturerName);
    }
}
