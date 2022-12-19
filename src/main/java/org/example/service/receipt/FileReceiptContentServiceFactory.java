package org.example.service.receipt;

import org.example.mapper.PurchasedProductMapper;
import org.example.service.PropertyValueExtractor;
import org.example.service.parser.DiscountCardParser;
import org.example.service.parser.ProductIdQuantityPairParser;
import org.example.service.validator.DiscountCardValidator;
import org.example.service.validator.ProductIdQuantityPairValidator;

public class FileReceiptContentServiceFactory extends ReceiptContentServiceFactory {
    private final PurchasedProductMapper purchasedProductMapper;
    private final ProductIdQuantityPairParser idQuantityPairParser;
    private final DiscountCardParser discountCardParser;
    private final DiscountCardValidator discountCardValidator;
    private final ProductIdQuantityPairValidator productIdQuantityPairValidator;
    private final PropertyValueExtractor propertyValueExtractor;

    public FileReceiptContentServiceFactory(PurchasedProductMapper purchasedProductMapper,
                                            ProductIdQuantityPairParser idQuantityPairParser,
                                            DiscountCardParser discountCardParser,
                                            DiscountCardValidator discountCardValidator,
                                            ProductIdQuantityPairValidator productIdQuantityPairValidator,
                                            PropertyValueExtractor propertyValueExtractor) {
        this.purchasedProductMapper = purchasedProductMapper;
        this.idQuantityPairParser = idQuantityPairParser;
        this.discountCardParser = discountCardParser;
        this.discountCardValidator = discountCardValidator;
        this.productIdQuantityPairValidator = productIdQuantityPairValidator;
        this.propertyValueExtractor = propertyValueExtractor;
    }

    @Override
    protected ReceiptContentService createReceiptContentService() {
        return new ReceiptContentServiceDecorator(
                new FileReceiptContentServiceDecorator(
                        new ConsoleReceiptContentService(purchasedProductMapper, idQuantityPairParser,
                                discountCardParser, discountCardValidator, productIdQuantityPairValidator,
                                propertyValueExtractor)
                )
        );
    }

}
