package org.example.service.parser;

import org.example.dto.ProductIdQuantityPair;

public class ProductIdQuantityPairParserImpl implements ProductIdQuantityPairParser {

    @Override
    public ProductIdQuantityPair parse(String source) {
        String[] split = source.split("-");
        long id = Long.parseLong(split[0]);
        int quantity = Integer.parseInt(split[1]);
        return new ProductIdQuantityPair(id, quantity);
    }
}
