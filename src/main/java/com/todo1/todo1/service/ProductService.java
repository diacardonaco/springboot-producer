package com.todo1.todo1.service;

import com.todo1.todo1.model.Product;
import com.todo1.todo1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getOne(long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<Product>();
        products.forEach(productList::add);
        return  productList;
    }

    public Product createProduct(Product product){
       return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProductById(long productId){
        productRepository.deleteById(productId);
    }

    public void deleteAllProducts(){
        productRepository.deleteAll();
    }

}
