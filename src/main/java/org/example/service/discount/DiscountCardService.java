package org.example.service.discount;

import org.example.entity.DiscountCard;

import java.util.Optional;

public interface DiscountCardService {

    boolean existsById(long id);

    Optional<DiscountCard> findById(long id);
}
