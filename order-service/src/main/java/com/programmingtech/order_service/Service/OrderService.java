package com.programmingtech.order_service.Service;

import com.programmingtech.order_service.Dto.InventoryResponse;
import com.programmingtech.order_service.Dto.OrderRequest;
import com.programmingtech.order_service.Dto.OrderlineItemDto;
import com.programmingtech.order_service.Model.Order;
import com.programmingtech.order_service.Model.OrderLineItem;
import com.programmingtech.order_service.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientbuilder;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, WebClient.Builder webClientbuilder) {
        this.orderRepository =orderRepository;
        this.restTemplate = restTemplate;
        this.webClientbuilder = webClientbuilder;
    }

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrdernumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderRequest.getOrderlineItemDtos()
                .stream()
                .map(this::maptodto)
                .toList();
        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems()
                                        .stream()
                                        .map(OrderLineItem::getSkuCode)
                                        .toList();
        System.out.println(skuCodes);



        InventoryResponse[] result = webClientbuilder.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder ->
                     uriBuilder.queryParam("skuCodes", skuCodes).build()
                )
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
//        String url = UriComponentsBuilder.fromUriString("http://localhost:8082/api/inventory")
//                .queryParam("skuCodes", skuCodes.toArray())
//                .toUriString();
//
//        // Call the inventory service using RestTemplate
//        InventoryResponse[] result = restTemplate.getForObject(url, InventoryResponse[].class);

        boolean orderInStock = Arrays.stream(result).allMatch(InventoryResponse::isInStock);

        System.out.println("orderInStock  "+orderInStock);
        if(orderInStock){
            orderRepository.save(order);
            return "order placed successfully";
        }else{
            throw new IllegalArgumentException("Order not in stock, please try again later");
        }


    }

    private OrderLineItem maptodto(OrderlineItemDto orderlineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderlineItemDto.getPrice());
        orderLineItem.setQuantity(orderlineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderlineItemDto.getSkuCode());

        return orderLineItem;
    }
}
