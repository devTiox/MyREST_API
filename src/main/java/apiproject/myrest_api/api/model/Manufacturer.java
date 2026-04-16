package apiproject.myrest_api.api.model;

import jakarta.persistence.*;

import java.util.List;

// Mówi JPA, że ta klasa ma być mapowana na tabelę w bazie danych.
@Entity
public class Manufacturer {
    // Oznacza pole jako klucz główny encji.
    @Id
    // Każe bazie generować id automatycznie, zwykle jako auto-increment/identity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;

    public Manufacturer(Long id, String name, String address, String email, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Manufacturer() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

}
