package org.example.service.cost;

import org.example.entity.Product;
import org.example.entity.PurchasedProduct;

import java.math.BigDecimal;
import java.util.List;

public interface CostService {

    BigDecimal calculateProductCost(Product product, int quantity);

    BigDecimal calculateTotalCostOfProducts(List<PurchasedProduct> purchasedProducts);

    void setTotalCostOfProducts(List<PurchasedProduct> purchasedProducts);
}
