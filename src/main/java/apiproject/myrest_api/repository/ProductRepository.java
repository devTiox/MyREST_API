package apiproject.myrest_api.repository;

import apiproject.myrest_api.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByManufacturerId(Long manufacturerId);

    Optional<Product> findByName(String productName);

    void deleteByName(String productName);

    @Query(value = """
    SELECT * FROM product p
    WHERE (:id IS NULL OR p.id = :id)
      AND (:manufacturerId IS NULL OR p.manufacturer_id = :manufacturerId)
      AND (:name IS NULL OR LOWER(p.name) LIKE CONCAT('%', CAST(:name AS text), '%'))
      AND (:maxPrice IS NULL OR p.price <= :maxPrice)
      AND (:minPrice IS NULL OR p.price >= :minPrice)
    """, nativeQuery = true)
    List<Product> searchProduct(
            @Param("id")                Long id,
            @Param("manufacturerId")    Long manufacturerId,
            @Param("name")              String name,
            @Param("maxPrice")          Double maxPrice,
            @Param("minPrice")          Double minPrice
    );

    @Query(value = """
    SELECT * FROM product 
    """,nativeQuery = true)
    List<Product> getAll();
}
