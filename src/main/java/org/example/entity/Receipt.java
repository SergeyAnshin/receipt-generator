package org.example.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Receipt extends GeneralEntity {
    private String title;
    private List<PurchasedProduct> purchasedProducts;
    private BigDecimal total;

    private Receipt(ReceiptBuilder builder) {
        super(builder.id);
        this.title = builder.title;
        this.purchasedProducts = builder.purchasedProducts;
        this.total = builder.total;
    }

    public static class ReceiptBuilder {
        private long id;
        private String title;
        private List<PurchasedProduct> purchasedProducts;
        private BigDecimal total;

        public ReceiptBuilder() {
        }

        public ReceiptBuilder id(long id) {
            this.id = id;
            return this;
        }

        public ReceiptBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ReceiptBuilder purchasedProducts(List<PurchasedProduct> purchasedProducts) {
            this.purchasedProducts = purchasedProducts;
            return this;
        }

        public ReceiptBuilder total(BigDecimal total) {
            this.total = total;
            return this;
        }

        public Receipt build() {
            return new Receipt(this);
        }
    }

    public static ReceiptBuilder builder() {
        return new ReceiptBuilder();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
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
        Receipt receipt = (Receipt) o;
        return title.equals(receipt.title) && purchasedProducts.equals(receipt.purchasedProducts)
                && total.equals(receipt.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, purchasedProducts, total);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id='" + super.getId() + '\'' +
                ", title='" + title +
                ", purchasedProducts=" + purchasedProducts +
                ", total=" + total +
                '}';
    }
}
