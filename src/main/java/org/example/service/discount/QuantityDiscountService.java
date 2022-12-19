package org.example.service.discount;

import org.example.entity.DiscountCard;
import org.example.entity.PurchasedProduct;
import org.example.service.util.PercentConverter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class QuantityDiscountService implements DiscountService {
    public static final int MINIMUM_QUANTITY_FOR_DISCOUNT = 5;
    private PercentConverter percentConverter;

    public QuantityDiscountService(PercentConverter percentConverter) {
        this.percentConverter = percentConverter;
    }

    @Override
    public void applyDiscountCardToPurchasedProducts(DiscountCard discountCard, List<PurchasedProduct> purchasedProducts) {
        purchasedProducts.stream()
                .filter(purchasedProduct -> purchasedProduct.getQuantity() > MINIMUM_QUANTITY_FOR_DISCOUNT)
                .forEach(purchasedProduct ->
                        applyDiscountToReceiptItem(discountCard.getDiscountInPercent(), purchasedProduct));
    }

    private void applyDiscountToReceiptItem(int discount, PurchasedProduct purchasedProduct) {
        BigDecimal total = purchasedProduct.getTotal();
        BigDecimal totalAtDiscount = total.multiply(percentConverter.convertPercentageToFraction(discount))
                .setScale(2, RoundingMode.CEILING);
        purchasedProduct.setTotal(totalAtDiscount);
    }
}
