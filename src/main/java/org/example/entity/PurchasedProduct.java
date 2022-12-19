package org.example.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class PurchasedProduct extends GeneralEntity {
    private Product product;
    private int quantity;
    private BigDecimal total;

    private PurchasedProduct(PurchasedProductBuilder builder) {
        super(builder.id);
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.total = builder.total;
    }

    public static class PurchasedProductBuilder {
        private long id;
        private Product product;
        private int quantity;
        private BigDecimal total;

        public PurchasedProductBuilder() {
        }

        public PurchasedProductBuilder id(long id) {
            this.id = id;
            return this;
        }

        public PurchasedProductBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public PurchasedProductBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public PurchasedProductBuilder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public PurchasedProduct build() {
            return new PurchasedProduct(this);
        }
    }

    public static PurchasedProductBuilder builder() {
        return new PurchasedProductBuilder();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PurchasedProduct that = (PurchasedProduct) o;
        return quantity == that.quantity && product.equals(that.product) && total.equals(that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, total);
    }

    @Override
    public String toString() {
        return "PurchasedProduct{" +
                "id='" + super.getId() + '\'' +
                ", product=" + product +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
