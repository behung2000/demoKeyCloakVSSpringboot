package com.example.demokeycloakvsspringboot.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


// postgresql
@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "Name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "Price", nullable = true)
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price
                && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    public String toString(){
        return "Product { Id=" + id + ", Name='" + name + "', Price=" + price
                + "}";
    }
}
