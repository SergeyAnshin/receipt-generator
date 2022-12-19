package org.example.service.discount;

import org.example.dao.DiscountCardDao;
import org.example.entity.DiscountCard;

import java.util.Optional;

public class DiscountCardServiceImpl implements DiscountCardService {
    private final DiscountCardDao discountCardDao;

    public DiscountCardServiceImpl(DiscountCardDao discountCardDao) {
        this.discountCardDao = discountCardDao;
    }

    @Override
    public boolean existsById(long id) {
        return discountCardDao.existsById(id);
    }

    @Override
    public Optional<DiscountCard> findById(long id) {
        return discountCardDao.findById(id);
    }
}
