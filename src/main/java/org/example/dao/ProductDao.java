package org.example.dao;

import org.example.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductDao {

    boolean existsById(long id);

    List<Product> findAllByIdIn(Set<Long> productIds);
}
