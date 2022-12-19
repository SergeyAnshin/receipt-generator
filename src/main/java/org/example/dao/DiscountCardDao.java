package org.example.dao;

import org.example.entity.DiscountCard;

import java.util.Optional;

public interface DiscountCardDao {

    boolean existsById(long id);

    Optional<DiscountCard> findById(long id);

}
