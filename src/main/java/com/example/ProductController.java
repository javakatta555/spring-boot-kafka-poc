package com.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductKafkaProducer productKafkaProducer;

    @GetMapping("/health")
    public String healthCheck(){
        return "I am healthy!";
    }

    @PostMapping("/product")
    public Product publishProduct(@RequestBody Product product) {
        productKafkaProducer.publish_without_key(product);
        return product;
    }
}
