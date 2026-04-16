package apiproject.myrest_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import apiproject.myrest_api.repository.ManufacturerRepository;
import apiproject.myrest_api.repository.ProductRepository;

// Uruchamia test z pełnym kontekstem Spring Boot, prawie tak jak przy starcie aplikacji.
@SpringBootTest(properties = {
        // W tym teście pomijamy konfigurację bazy, żeby sprawdzić sam start aplikacji.
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,"
                + "org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration"
})
class MyRestApiApplicationTests {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ManufacturerRepository manufacturerRepository;

    // Oznacza pojedynczą metodę testową JUnit.
    @Test
    void contextLoads() {
    }
}
