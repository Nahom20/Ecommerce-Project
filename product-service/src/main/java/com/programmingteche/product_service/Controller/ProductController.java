package com.programmingteche.product_service.Controller;

import com.programmingteche.product_service.Dto.ProductRequest;
import com.programmingteche.product_service.Dto.ProductResponse;
import com.programmingteche.product_service.Model.Product;
import com.programmingteche.product_service.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProcuct(){
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
