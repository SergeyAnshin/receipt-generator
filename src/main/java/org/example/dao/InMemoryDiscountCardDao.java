package org.example.dao;

import org.example.entity.DiscountCard;
import org.example.entity.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class InMemoryDiscountCardDao implements DiscountCardDao {
    private Map<Long, DiscountCard> discountCards;

    public InMemoryDiscountCardDao(Map<Long, DiscountCard> discountCards) {
        this.discountCards = discountCards;
        init();
    }

    private void init() {
        discountCards.put(1L, DiscountCard.builder().id(1).discountInPercent(10).build());
        discountCards.put(2L, DiscountCard.builder().id(2).discountInPercent(15).build());
        discountCards.put(3L, DiscountCard.builder().id(3).discountInPercent(32).build());
    }

    @Override
    public boolean existsById(long id) {
        return discountCards.containsKey(id);
    }

    @Override
    public Optional<DiscountCard> findById(long id) {
        return Optional.ofNullable(discountCards.get(id));
    }
}
