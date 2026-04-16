package apiproject.myrest_api.repository;

import apiproject.myrest_api.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
