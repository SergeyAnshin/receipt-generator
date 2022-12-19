package org.example.dto;

import org.example.entity.DiscountCard;
import org.example.entity.PurchasedProduct;

import java.util.List;
import java.util.Objects;

public class ReceiptContent {
    private String title;
    private DiscountCard discountCard;
    private List<PurchasedProduct> purchasedProducts;

    public ReceiptContent(DiscountCard discountCard, List<PurchasedProduct> purchasedProducts) {
        this.discountCard = discountCard;
        this.purchasedProducts = purchasedProducts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptContent that = (ReceiptContent) o;
        return Objects.equals(title, that.title) && Objects.equals(discountCard, that.discountCard) && Objects.equals(purchasedProducts, that.purchasedProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, discountCard, purchasedProducts);
    }

    @Override
    public String toString() {
        return "ReceiptContent{" +
                "title='" + title + '\'' +
                ", discountCard=" + discountCard +
                ", purchasedProducts=" + purchasedProducts +
                '}';
    }
}
