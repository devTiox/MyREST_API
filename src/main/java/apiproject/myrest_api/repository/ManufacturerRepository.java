package apiproject.myrest_api.repository;

import apiproject.myrest_api.api.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
