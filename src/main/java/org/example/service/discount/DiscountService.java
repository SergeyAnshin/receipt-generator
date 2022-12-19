package org.example.service.discount;

import org.example.entity.DiscountCard;
import org.example.entity.PurchasedProduct;

import java.util.List;


public interface DiscountService {

    void applyDiscountCardToPurchasedProducts(DiscountCard discountCard, List<PurchasedProduct> purchasedProducts);

}
