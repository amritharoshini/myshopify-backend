package com.myshoppify.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myshoppify.productservice.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
