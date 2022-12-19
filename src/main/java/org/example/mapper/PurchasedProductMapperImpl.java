package org.example.mapper;

import org.example.entity.Product;
import org.example.dto.ProductIdQuantityPair;
import org.example.entity.PurchasedProduct;

public class PurchasedProductMapperImpl implements PurchasedProductMapper {

    @Override
    public PurchasedProduct purchasedProductFromIdQuantityPair(ProductIdQuantityPair idQuantityPair) {
        return PurchasedProduct.builder()
                .product(Product.builder()
                        .id(idQuantityPair.getId())
                        .build())
                .quantity(idQuantityPair.getQuantity())
                .build();
    }
}
