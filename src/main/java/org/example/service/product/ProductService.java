package org.example.service.product;

import org.example.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<Product> findAllByIdIn(Set<Long> productIds);

}
