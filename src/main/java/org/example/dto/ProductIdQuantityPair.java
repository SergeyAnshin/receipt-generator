package org.example.dto;

import java.util.Objects;

public class ProductIdQuantityPair {
    private long id;
    private int quantity;

    public ProductIdQuantityPair(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductIdQuantityPair that = (ProductIdQuantityPair) o;
        return id == that.id && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @Override
    public String toString() {
        return "ProductIdQuantityPair{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
