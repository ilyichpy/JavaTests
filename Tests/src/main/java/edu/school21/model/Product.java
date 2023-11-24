package edu.school21.model;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private int price;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setPrice(int price) {this.price = price;}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product p = (Product) o;
        return p.price == this.price
                && p.name.equals(this.name)
                && p.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("id: " + id);
        res.append("\nname: " + name);
        res.append("\nprice: " + price);
        return res.toString();
    }


}
