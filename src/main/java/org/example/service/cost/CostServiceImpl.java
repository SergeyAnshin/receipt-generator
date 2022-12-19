package org.example.service.cost;

import org.example.entity.Product;
import org.example.entity.PurchasedProduct;

import java.math.BigDecimal;
import java.util.List;

public class CostServiceImpl implements CostService {

    @Override
    public BigDecimal calculateProductCost(Product product, int quantity) {
        return product.getPrice()
                .multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public BigDecimal calculateTotalCostOfProducts(List<PurchasedProduct> purchasedProducts) {
        return purchasedProducts.stream()
                .map(PurchasedProduct::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void setTotalCostOfProducts(List<PurchasedProduct> purchasedProducts) {
        purchasedProducts.forEach(purchasedProduct -> {
            BigDecimal totalCost = calculateProductCost(purchasedProduct.getProduct(),
                    purchasedProduct.getQuantity());
            purchasedProduct.setTotal(totalCost);
        });
    }
}
