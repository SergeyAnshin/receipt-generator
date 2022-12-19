package org.example.dao;

import org.example.entity.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryProductDao implements ProductDao {
    private Map<Long, Product> products;

    public InMemoryProductDao(Map<Long, Product> products) {
        this.products = products;
        init();
    }

    private void init() {
        products.put(1L, Product.builder().id(1).description("Apple").price(BigDecimal.valueOf(3.11)).build());
        products.put(2L, Product.builder().id(2).description("Phone").price(BigDecimal.valueOf(1.75)).build());
        products.put(3L, Product.builder().id(3).description("Car").price(BigDecimal.valueOf(9.23)).build());
    }

    @Override
    public boolean existsById(long id) {
        return products.containsKey(id);
    }

    @Override
    public List<Product> findAllByIdIn(Set<Long> productIds) {
        return productIds.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
