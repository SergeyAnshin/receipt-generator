package org.example.service.receipt;

import org.example.entity.DiscountCard;
import org.example.entity.PurchasedProduct;
import org.example.entity.Product;
import org.example.entity.Receipt;
import org.example.dto.ReceiptContent;
import org.example.service.cost.CostService;
import org.example.service.discount.DiscountCardService;
import org.example.service.discount.DiscountService;
import org.example.service.product.ProductService;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConsoleArgumentsReceiptGenerator implements ReceiptGenerator {
    private final DiscountService discountService;
    private final CostService costService;
    private final ProductService productService;
    private final DiscountCardService discountCardService;

    public ConsoleArgumentsReceiptGenerator(DiscountService discountService, CostService costService,
                                            ProductService productService, DiscountCardService discountCardService) {
        this.discountService = discountService;
        this.costService = costService;
        this.productService = productService;
        this.discountCardService = discountCardService;
    }

    @Override
    public Receipt generateReceipt(ReceiptContent source) {
        Optional<DiscountCard> optionalDiscountCard = Optional.ofNullable(source.getDiscountCard());
        List<PurchasedProduct> purchasedProducts = source.getPurchasedProducts();
        optionalDiscountCard.ifPresent(this::setFullDiscountCardInfo);
        setFullProductInfoToPurchasedProducts(purchasedProducts);
        costService.setTotalCostOfProducts(purchasedProducts);
        optionalDiscountCard.ifPresent(discountCard ->
                discountService.applyDiscountCardToPurchasedProducts(discountCard, purchasedProducts));
        BigDecimal costOfProducts = costService.calculateTotalCostOfProducts(purchasedProducts);
        return Receipt.builder()
                .title(source.getTitle())
                .purchasedProducts(purchasedProducts)
                .total(costOfProducts)
                .build();
    }

    private void setFullDiscountCardInfo(DiscountCard discountCard) {
        Optional<DiscountCard> existingCard = discountCardService.findById(discountCard.getId());
        existingCard.ifPresent(card -> discountCard.setDiscountInPercent(card.getDiscountInPercent()));
    }

    private void setFullProductInfoToPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        Set<Long> productIds = purchasedProducts.stream()
                .map(PurchasedProduct::getProduct)
                .map(Product::getId)
                .collect(Collectors.toSet());
        Map<Long, Product> existingProducts = productService.findAllByIdIn(productIds)
                .stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        purchasedProducts.forEach(purchasedProduct -> {
            long productId = purchasedProduct.getProduct().getId();
            purchasedProduct.setProduct(existingProducts.get(productId));
        });
    }
}
