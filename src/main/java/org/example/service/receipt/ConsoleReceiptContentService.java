package org.example.service.receipt;

import org.example.entity.DiscountCard;
import org.example.dto.ProductIdQuantityPair;
import org.example.entity.PurchasedProduct;
import org.example.dto.ReceiptContent;
import org.example.exception.EntityNotExistsException;
import org.example.exception.MultipleDiscountCardsException;
import org.example.mapper.PurchasedProductMapper;
import org.example.service.PropertyValueExtractor;
import org.example.service.parser.DiscountCardParser;
import org.example.service.parser.ProductIdQuantityPairParser;
import org.example.service.validator.DiscountCardValidator;
import org.example.service.validator.ProductIdQuantityPairValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConsoleReceiptContentService implements ReceiptContentService<String[]> {
    public static final String INCORRECT_ARGUMENT_TYPE_MESSAGE_PROPERTY_KEY =  "error.message.incorrect.argument.type";
    public static final String PRODUCT_NOT_EXISTS_MESSAGE = "Product does not exist!";
    public static final String DISCOUNT_CARD_NOT_EXISTS_MESSAGE = "Discount card does not exist!";
    public static final String MULTIPLY_DISCOUNT_CARDS_MESSAGE = "You have already applied the discount card!";
    private final PurchasedProductMapper mapper;
    private final ProductIdQuantityPairParser pairParser;
    private final DiscountCardParser discountCardParser;
    private final DiscountCardValidator discountCardValidator;
    private final ProductIdQuantityPairValidator productIdQuantityPairValidator;
    private final PropertyValueExtractor propertyValueExtractor;

    public ConsoleReceiptContentService(PurchasedProductMapper mapper,
                                        ProductIdQuantityPairParser pairParser,
                                        DiscountCardParser discountCardParser,
                                        DiscountCardValidator discountCardValidator,
                                        ProductIdQuantityPairValidator productIdQuantityPairValidator,
                                        PropertyValueExtractor propertyValueExtractor) {
        this.mapper = mapper;
        this.pairParser = pairParser;
        this.discountCardParser = discountCardParser;
        this.discountCardValidator = discountCardValidator;
        this.productIdQuantityPairValidator = productIdQuantityPairValidator;
        this.propertyValueExtractor = propertyValueExtractor;
    }

    @Override
    public ReceiptContent getReceiptContent(String[] source) {
        List<PurchasedProduct> purchasedProducts = new ArrayList<>();
        DiscountCard discountCard = null;
        PurchasedProduct purchasedProduct;
        DiscountCard parsedCard;
        for (String argument :  source) {
            checkIfArgumentFileName(argument);
            purchasedProduct = getPurchasedProductFromArgument(argument);
            addPurchasedProductIfNotNullToPurchasedProducts(purchasedProduct, purchasedProducts);
            parsedCard = getDiscountCardFromArgument(argument);
            if (parsedCard != null) {
                checkIfCardAlreadySet(discountCard);
                discountCard = parsedCard;
            }
        }
        return new ReceiptContent(discountCard, purchasedProducts);
    }

    private PurchasedProduct getPurchasedProductFromArgument(String argument) {
        PurchasedProduct purchasedProduct = null;
        if (productIdQuantityPairValidator.isIdQuantityPair(argument)) {
            ProductIdQuantityPair idQuantityPair = pairParser.parse(argument);
            purchasedProduct = mapPairToPurchasedProduct(idQuantityPair);
        }
        return purchasedProduct;
    }

    private PurchasedProduct mapPairToPurchasedProduct(ProductIdQuantityPair idQuantityPair) {
        if (productIdQuantityPairValidator.isExistingProductId(idQuantityPair.getId())) {
             return mapper.purchasedProductFromIdQuantityPair(idQuantityPair);
        } else {
            throw new EntityNotExistsException(PRODUCT_NOT_EXISTS_MESSAGE);
        }
    }

    private void addPurchasedProductIfNotNullToPurchasedProducts(PurchasedProduct purchasedProduct, List<PurchasedProduct> purchasedProducts) {
        if (purchasedProduct != null) {
            purchasedProducts.add(purchasedProduct);
        }
    }

    private DiscountCard getDiscountCardFromArgument(String argument) {
        DiscountCard discountCard = null;
        if (discountCardValidator.isDiscountCard(argument)) {
            DiscountCard parsedCard = discountCardParser.parse(argument);
            validateParsedDiscountCard(parsedCard);
            discountCard = parsedCard;
        }
        return discountCard;
    }

    private void validateParsedDiscountCard(DiscountCard parsedCard) {
        if (!discountCardValidator.isExistingDiscountCard(parsedCard)) {
            throw new EntityNotExistsException(DISCOUNT_CARD_NOT_EXISTS_MESSAGE);
        }
    }

    private void checkIfCardAlreadySet(DiscountCard discountCard) {
        if (discountCard != null) {
            throw new MultipleDiscountCardsException(MULTIPLY_DISCOUNT_CARDS_MESSAGE);
        }
    }

    private void checkIfArgumentFileName(String argument) {
        File file = new File(argument);
        if (file.exists()) {
            throw new IllegalArgumentException(
                    propertyValueExtractor.getPropertyValue(INCORRECT_ARGUMENT_TYPE_MESSAGE_PROPERTY_KEY)
            );
        }
    }
}
