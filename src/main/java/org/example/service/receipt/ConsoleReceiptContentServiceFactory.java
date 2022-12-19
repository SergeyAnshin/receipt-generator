package org.example.service.receipt;

import org.example.mapper.PurchasedProductMapper;
import org.example.service.parser.DiscountCardParser;
import org.example.service.parser.ProductIdQuantityPairParser;
import org.example.service.validator.DiscountCardValidator;
import org.example.service.validator.ProductIdQuantityPairValidator;

public class ConsoleReceiptContentServiceFactory extends ReceiptContentServiceFactory {
    private final PurchasedProductMapper purchasedProductMapper;
    private final ProductIdQuantityPairParser idQuantityPairParser;
    private final DiscountCardParser discountCardParser;
    private final DiscountCardValidator discountCardValidator;
    private final ProductIdQuantityPairValidator productIdQuantityPairValidator;

    public ConsoleReceiptContentServiceFactory(PurchasedProductMapper purchasedProductMapper,
                                               ProductIdQuantityPairParser idQuantityPairParser,
                                               DiscountCardParser discountCardParser,
                                               DiscountCardValidator discountCardValidator,
                                               ProductIdQuantityPairValidator productIdQuantityPairValidator) {
        this.purchasedProductMapper = purchasedProductMapper;
        this.idQuantityPairParser = idQuantityPairParser;
        this.discountCardParser = discountCardParser;
        this.discountCardValidator = discountCardValidator;
        this.productIdQuantityPairValidator = productIdQuantityPairValidator;
    }

    @Override
    protected ReceiptContentService createReceiptContentService() {
        return new ConsoleReceiptContentService(purchasedProductMapper, idQuantityPairParser, discountCardParser,
                discountCardValidator, productIdQuantityPairValidator);
    }

}
