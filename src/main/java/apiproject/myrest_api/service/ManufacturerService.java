package apiproject.myrest_api.service;

import apiproject.myrest_api.api.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ManufacturerService {
    public ManufacturerService(){
    }

    public Manufacturer getManufacturer(int id) {
        //TODO: Get from db
        return new Manufacturer();
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        //TODO: Put to db

        return manufacturer;
    }

    public List<Manufacturer> createManufacturers(List<Manufacturer> manufacturers) {
        for(Manufacturer manufacturer : manufacturers){
            //TODO: Put all to db
        }
        return manufacturers;
    }

    public List<Manufacturer> getManufacturers(List<Integer> ids) {
        //TODO: Get all from db
        return List.of();
    }

    public Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer) {
        Manufacturer manufacturer = null;
        //TODO: get Manufacturer by id
        if (manufacturer != null && manufacturer.getId() == id) {
            manufacturer.setName(updatedManufacturer.getName());
            manufacturer.setAddress(updatedManufacturer.getAddress());
            manufacturer.setEmail(updatedManufacturer.getEmail());
            manufacturer.setPhone(updatedManufacturer.getPhone());
            return manufacturer;
        }
        return new Manufacturer();
    }

    public Manufacturer patchManufacturer(int id, Map<String, Object> updates) {
        Manufacturer manufacturer = null;
        //TODO: get Manufacturer by id
        if (manufacturer != null) {
            if (updates.containsKey("name")) {
                manufacturer.setName((String) updates.get("name"));
            }
            if(updates.containsKey("address")) {
                manufacturer.setAddress((String) updates.get("address"));
            }
            if(updates.containsKey("email")){
                manufacturer.setEmail((String) updates.get("email"));
            }
            if(updates.containsKey("phone")){
                manufacturer.setPhone((String) updates.get("phone"));
            }
        }
        return new Manufacturer();
    }

    public Manufacturer deleteManufacturer(int id) {
        //TODO: delete from db
        return new Manufacturer();
    }
}
