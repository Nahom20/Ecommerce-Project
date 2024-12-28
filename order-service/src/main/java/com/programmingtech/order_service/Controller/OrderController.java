package com.programmingtech.order_service.Controller;

import com.programmingtech.order_service.Dto.OrderRequest;
import com.programmingtech.order_service.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackmethod")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("order placed successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> fallbackmethod(OrderRequest orderRequest, RuntimeException exception){
        return new ResponseEntity<>("Opps, Something went wrong, please try after sometime", HttpStatus.OK);
    }
}
