package org.example.service.parser;

import org.example.dto.ProductIdQuantityPair;

public interface ProductIdQuantityPairParser {

    ProductIdQuantityPair parse(String source);
    
}
