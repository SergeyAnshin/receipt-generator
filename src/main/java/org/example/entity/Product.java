package org.example.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product extends GeneralEntity {
    private String description;
    private BigDecimal price;

    private Product(ProductBuilder builder) {
        super(builder.id);
        this.description = builder.description;
        this.price = builder.price;
    }

    public static class ProductBuilder {
        private long id;
        private String description;
        private BigDecimal price;

        public ProductBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return description.equals(product.description) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + super.getId() + '\'' +
                ", description='" + description +
                ", price=" + price +
                '}';
    }
}
