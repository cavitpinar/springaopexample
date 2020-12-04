package com.magnesiatech.springaopexample.service;

import com.magnesiatech.springaopexample.repository.entity.Product;
import com.magnesiatech.springaopexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(Product product){
        productRepository.save(product);
    }

}
