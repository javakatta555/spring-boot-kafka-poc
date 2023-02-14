package com.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductKafkaProducer productKafkaProducer;

    @GetMapping("/health")
    public String healthCheck(){
        return "I am healthy!";
    }

    @GetMapping("/product")
    public String publishProduct() throws JsonProcessingException {
        String message = "This is product message";
        Product product = new Product("1","pen");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(product);
        productKafkaProducer.publish_without_key("product",jsonString);
        return jsonString;
    }
}
