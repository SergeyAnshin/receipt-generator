package org.example.service.validator;

import org.example.entity.DiscountCard;

public interface DiscountCardValidator {
    boolean isDiscountCard(String argument);

    boolean isExistingDiscountCard(DiscountCard discountCard);
    
}
