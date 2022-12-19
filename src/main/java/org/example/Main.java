package org.example;

import org.example.dao.DiscountCardDao;
import org.example.dao.InMemoryDiscountCardDao;
import org.example.dao.InMemoryProductDao;
import org.example.dao.ProductDao;
import org.example.dto.ReceiptContent;
import org.example.entity.Receipt;
import org.example.mapper.PurchasedProductMapper;
import org.example.mapper.PurchasedProductMapperImpl;
import org.example.service.PropertyValueExtractor;
import org.example.service.cost.CostService;
import org.example.service.cost.CostServiceImpl;
import org.example.service.discount.DiscountCardService;
import org.example.service.discount.DiscountCardServiceImpl;
import org.example.service.parser.DiscountCardParser;
import org.example.service.parser.DiscountCardParserImpl;
import org.example.service.discount.DiscountService;
import org.example.service.discount.QuantityDiscountService;
import org.example.service.parser.ProductIdQuantityPairParser;
import org.example.service.parser.ProductIdQuantityPairParserImpl;
import org.example.service.product.ProductService;
import org.example.service.product.ProductServiceImpl;
import org.example.service.receipt.*;
import org.example.service.sender.ConsoleReceiptSender;
import org.example.service.sender.FileReceiptSenderDecorator;
import org.example.service.sender.ReceiptSender;
import org.example.service.sender.ReceiptSenderDecorator;
import org.example.service.util.PercentConverter;
import org.example.service.validator.DiscountCardValidator;
import org.example.service.validator.DiscountCardValidatorImpl;
import org.example.service.validator.ProductIdQuantityPairValidator;
import org.example.service.validator.ProductIdQuantityPairValidatorImpl;

import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static final String ARGUMENT_TYPE_PROPERTY_KEY = "argument.type";
    private static final PropertyValueExtractor propertyValueExtractor;
    private static ReceiptContentServiceFactory receiptContentServiceFactory;
    private static final ProductService productService;
    private static final DiscountCardDao discountCardDao;
    private static final ProductDao productDao;
    private static final DiscountCardService discountCardService;
    private static final PurchasedProductMapper purchasedProductMapper;
    private static final ProductIdQuantityPairParser idQuantityPairParser;
    private static final DiscountCardParser discountCardParser;
    private static final DiscountCardValidator discountCardValidator;
    private static final ProductIdQuantityPairValidator productIdQuantityPairValidator;
    private static final DiscountService discountService;
    private static final ReceiptGenerator receiptGenerator;
    public static final ReceiptSender receiptSender;
    public static final CostService costService;

    static {
        propertyValueExtractor = new PropertyValueExtractor();
        productDao = new InMemoryProductDao(new ConcurrentHashMap<>());
        productService = new ProductServiceImpl(productDao);
        discountCardDao = new InMemoryDiscountCardDao(new ConcurrentHashMap<>());
        discountCardService = new DiscountCardServiceImpl(discountCardDao);
        purchasedProductMapper = new PurchasedProductMapperImpl();
        idQuantityPairParser = new ProductIdQuantityPairParserImpl();
        discountCardParser = new DiscountCardParserImpl();
        discountCardValidator = new DiscountCardValidatorImpl(discountCardService);
        productIdQuantityPairValidator = new ProductIdQuantityPairValidatorImpl(productDao);
        discountService = new QuantityDiscountService(new PercentConverter());
        costService = new CostServiceImpl();
        receiptGenerator = new ConsoleArgumentsReceiptGenerator(discountService, costService, productService,
                discountCardService);
        receiptSender = new ReceiptSenderDecorator(new FileReceiptSenderDecorator(new ConsoleReceiptSender()));
    }

    public static void main(String[] args) {
        configure();
        ReceiptContent receiptContent = receiptContentServiceFactory.getReceiptContent(args);
        Receipt receipt = receiptGenerator.generateReceipt(receiptContent);
        receiptSender.sendReceipt(receipt);
    }

    private static void configure() {
        String argumentType = propertyValueExtractor.getPropertyValue(ARGUMENT_TYPE_PROPERTY_KEY);
        receiptContentServiceFactory = getReceiptContentServiceFactory(argumentType);
    }

    private static ReceiptContentServiceFactory getReceiptContentServiceFactory(String argumentType) {
        switch (argumentType) {
            case "console": return new ConsoleReceiptContentServiceFactory(purchasedProductMapper, idQuantityPairParser,
                    discountCardParser, discountCardValidator, productIdQuantityPairValidator, propertyValueExtractor);
            case "file": return new FileReceiptContentServiceFactory(purchasedProductMapper, idQuantityPairParser,
                    discountCardParser, discountCardValidator, productIdQuantityPairValidator, propertyValueExtractor);
            default: throw new IllegalArgumentException();
        }
    }
}