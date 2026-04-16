package apiproject.myrest_api.repository;

import apiproject.myrest_api.api.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Optional<Manufacturer> findByName(String manufacturerName);

    @Query(value = """
            SELECT * FROM manufacturer m
            WHERE (:id IS NULL OR m.id = :id)
              AND (:name IS NULL OR LOWER(m.name) LIKE CONCAT('%', CAST(:name AS text), '%'))
              AND (:address IS NULL OR LOWER(m.address) LIKE CONCAT('%', CAST(:address AS text), '%'))
              AND (:email IS NULL OR LOWER(m.email) LIKE CONCAT('%', CAST(:email AS text), '%'))
              AND (:phone IS NULL OR m.phone LIKE CONCAT('%', CAST(:phone AS text), '%'))
    """, nativeQuery = true)
    List<Manufacturer> searchManufacturer(
            @Param("id")        Long id,
            @Param("name")      String name,
            @Param("address")   String address,
            @Param("email")     String email,
            @Param("phone")     String phone
    );
}
