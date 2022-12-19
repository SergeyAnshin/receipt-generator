package org.example.service.product;

import org.example.dao.ProductDao;
import org.example.entity.Product;
import org.example.exception.EntityNotExistsException;

import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAllByIdIn(Set<Long> productIds) {
        List<Product> foundExistingProducts = productDao.findAllByIdIn(productIds);
        checkIfAllProductsWereFound(productIds, foundExistingProducts);
        return foundExistingProducts;
    }

    private void checkIfAllProductsWereFound(Set<Long> productIds, List<Product> foundExistingProducts) {
        if (productIds.size() > foundExistingProducts.size()) {
            throw new EntityNotExistsException();
        }
    }
}
