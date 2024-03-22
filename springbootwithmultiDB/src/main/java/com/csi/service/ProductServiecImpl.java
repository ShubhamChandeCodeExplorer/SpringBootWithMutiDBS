package com.csi.service;

import com.csi.model.Product;
import com.csi.repo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiecImpl {
    @Autowired
    private ProductRepository productRepositoryImpl;

    public Product save(Product product){
        return productRepositoryImpl.save(product);
    }

    public Optional<Product> findByProdId(int prodId){
        return productRepositoryImpl.findById(prodId);
    }

    public Product updateProduct(Product product){
        return productRepositoryImpl.save(product);
    }

    public void deleteByProdId(int prodId){
        productRepositoryImpl.deleteById(prodId);
    }

    public void deleteAll(){
        productRepositoryImpl.deleteAll();
    }
}
