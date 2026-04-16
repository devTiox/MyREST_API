package apiproject.myrest_api.service;

import apiproject.myrest_api.api.exception.ResourceNotFoundException;
import apiproject.myrest_api.api.model.Manufacturer;
import apiproject.myrest_api.api.model.ManufacturerRequest;
import apiproject.myrest_api.api.model.Product;
import apiproject.myrest_api.repository.ManufacturerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository){
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer getManufacturer(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));
    }

    public Manufacturer getManufacturer(String name) {
        return manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));
    }

    public Manufacturer createManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturerEntity = toManufacturer(manufacturerRequest);
        return manufacturerRepository.save(manufacturerEntity);
    }

    public List<Manufacturer> createManufacturers(List<ManufacturerRequest> manufacturerRequests) {
        List<Manufacturer> manufacturerEntities = manufacturerRequests.stream()
                .map(this::toManufacturer)
                .toList();
        return manufacturerRepository.saveAll(manufacturerEntities);
    }

    public List<Manufacturer> getManufacturers(List<Long> ids) {
        return manufacturerRepository.findAllById(ids);
    }

    public Manufacturer updateManufacturer(Long id, ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturerEntity = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        manufacturerEntity.setName(manufacturerRequest.getName());
        manufacturerEntity.setAddress(manufacturerRequest.getAddress());
        manufacturerEntity.setEmail(manufacturerRequest.getEmail());
        manufacturerEntity.setPhone(manufacturerRequest.getPhone());

        return manufacturerRepository.save(manufacturerEntity);
    }

    public Manufacturer updateManufacturer(String name, ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturerEntity = manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        manufacturerEntity.setName(manufacturerRequest.getName());
        manufacturerEntity.setAddress(manufacturerRequest.getAddress());
        manufacturerEntity.setEmail(manufacturerRequest.getEmail());
        manufacturerEntity.setPhone(manufacturerRequest.getPhone());

        return manufacturerRepository.save(manufacturerEntity);
    }

    public Manufacturer patchManufacturer(Long id, Map<String, Object> updates) {
        Manufacturer manufacturerEntity = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        return patchManufacturer(manufacturerEntity, updates);
    }

    public Manufacturer patchManufacturer(String name, Map<String, Object> updates) {
        Manufacturer manufacturerEntity = manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));

        return patchManufacturer(manufacturerEntity, updates);
    }

    private Manufacturer patchManufacturer(Manufacturer manufacturerEntity, Map<String, Object> updates) {

        if (updates.containsKey("name")) {
            manufacturerEntity.setName((String) updates.get("name"));
        }
        if(updates.containsKey("address")) {
            manufacturerEntity.setAddress((String) updates.get("address"));
        }
        if(updates.containsKey("email")){
            manufacturerEntity.setEmail((String) updates.get("email"));
        }
        if(updates.containsKey("phone")){
            manufacturerEntity.setPhone((String) updates.get("phone"));
        }

        return manufacturerRepository.save(manufacturerEntity);
    }

    public Manufacturer deleteManufacturer(Long id) {
        Manufacturer manufacturerEntity = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));
        manufacturerRepository.deleteById(id);
        return manufacturerEntity;
    }

    public Manufacturer deleteManufacturer(String name) {
        Manufacturer manufacturerEntity = manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Manufacturer not found"));
        manufacturerRepository.deleteById(manufacturerEntity.getId());
        return manufacturerEntity;
    }

    private Manufacturer toManufacturer(ManufacturerRequest manufacturerRequest) {
        Manufacturer manufacturerEntity = new Manufacturer();
        manufacturerEntity.setName(manufacturerRequest.getName());
        manufacturerEntity.setAddress(manufacturerRequest.getAddress());
        manufacturerEntity.setEmail(manufacturerRequest.getEmail());
        manufacturerEntity.setPhone(manufacturerRequest.getPhone());
        return manufacturerEntity;
    }
}
