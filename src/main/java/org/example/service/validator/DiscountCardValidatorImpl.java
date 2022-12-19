package org.example.service.validator;

import org.example.entity.DiscountCard;
import org.example.service.discount.DiscountCardService;

import java.util.regex.Pattern;

public class DiscountCardValidatorImpl implements DiscountCardValidator {
    public static final String DISCOUNT_CARD_REGEX = "card-[0-9]+";
    private final DiscountCardService discountCardService;

    public DiscountCardValidatorImpl(DiscountCardService discountCardService) {
        this.discountCardService = discountCardService;
    }

    @Override
    public boolean isDiscountCard(String argument) {
        return Pattern.matches(DISCOUNT_CARD_REGEX, argument);
    }

    public boolean isExistingDiscountCard(DiscountCard discountCard) {
        return discountCardService.existsById(discountCard.getId());
    }
}
