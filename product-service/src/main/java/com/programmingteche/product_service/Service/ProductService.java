package com.programmingteche.product_service.Service;

import com.programmingteche.product_service.Dto.ProductRequest;
import com.programmingteche.product_service.Dto.ProductResponse;
import com.programmingteche.product_service.Model.Product;
import com.programmingteche.product_service.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
//        log.info("Product with ID "+ product.getId()+ " is saved");
    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(this::convertToProductResponse).toList();
    }

    private ProductResponse convertToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());

        return productResponse;
    }
}
