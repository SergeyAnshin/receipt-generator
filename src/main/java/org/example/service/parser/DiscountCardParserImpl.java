package org.example.service.parser;

import org.example.entity.DiscountCard;

public class DiscountCardParserImpl implements DiscountCardParser {

    @Override
    public DiscountCard parse(String source) {
        String[] stringWithCardId = source.split("-");
        long cardId = Long.parseLong(stringWithCardId[1]);
        return DiscountCard.builder().id(cardId).build();
    }
}
