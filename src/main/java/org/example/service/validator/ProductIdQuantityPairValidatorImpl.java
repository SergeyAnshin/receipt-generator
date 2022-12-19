package org.example.service.validator;

import org.example.dao.ProductDao;

import java.util.regex.Pattern;

public class ProductIdQuantityPairValidatorImpl implements ProductIdQuantityPairValidator {
    public static final String ID_QUANTITY_REGEX = "[0-9]+-[0-9]+";
    private final ProductDao productDao;

    public ProductIdQuantityPairValidatorImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public boolean isIdQuantityPair(String argument) {
        return Pattern.matches(ID_QUANTITY_REGEX, argument);
    }

    @Override
    public boolean isExistingProductId(long id) {
        return productDao.existsById(id);
    }
}
