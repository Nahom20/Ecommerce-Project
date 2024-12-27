package com.programmingtech.order_service.Dto;

import java.util.List;

public class OrderRequest {
    List<OrderlineItemDto> orderlineItemDtos;

    public OrderRequest() {
    }

    public OrderRequest(List<OrderlineItemDto> orderlineItemDtos) {
        this.orderlineItemDtos = orderlineItemDtos;
    }

    public List<OrderlineItemDto> getOrderlineItemDtos() {
        return orderlineItemDtos;
    }

    public void setOrderlineItemDtos(List<OrderlineItemDto> orderlineItemDtos) {
        this.orderlineItemDtos = orderlineItemDtos;
    }
}
