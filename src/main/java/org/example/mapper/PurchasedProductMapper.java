package org.example.mapper;

import org.example.dto.ProductIdQuantityPair;
import org.example.entity.PurchasedProduct;

public interface PurchasedProductMapper {

    PurchasedProduct purchasedProductFromIdQuantityPair(ProductIdQuantityPair idQuantityPair);
}
