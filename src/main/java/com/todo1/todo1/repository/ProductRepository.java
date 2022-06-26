package com.todo1.todo1.repository;

import com.todo1.todo1.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {
}
