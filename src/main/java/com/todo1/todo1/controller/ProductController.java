package com.todo1.todo1.controller;

import com.todo1.todo1.model.Product;
import com.todo1.todo1.service.ProductService;
import com.todo1.todo1.stream.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private KafkaProducer productoKafka;

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
    public ResponseEntity<Product> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product productCreated = productService.createProduct(product);
        return new ResponseEntity(productCreated, HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity(updatedProduct, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Product> productById(@PathVariable long productId){
        Optional<Product> product = productService.getOne(productId);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOne/{id}")
    public void deleteById(@PathVariable("id") long id) {
         productService.deleteProductById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        productService.deleteAllProducts();
    }

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public ResponseEntity<Product> inventario() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping(value = "/send/{message}")
    public String sendMessage(@PathVariable String message) {

        String response = "succesfull process";
        try {
            productoKafka.send(message);
        }catch (Exception e){
            response = "Unknowed error";
        }
        return  response;
    }

}
