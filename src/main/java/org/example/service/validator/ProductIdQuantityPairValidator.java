package org.example.service.validator;

public interface ProductIdQuantityPairValidator {

    boolean isIdQuantityPair(String argument);

    boolean isExistingProductId(long id);

}
