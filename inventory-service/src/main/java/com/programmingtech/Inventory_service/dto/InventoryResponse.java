package com.programmingtech.Inventory_service.dto;

public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;

    public InventoryResponse() {
    }

    public InventoryResponse(boolean isInStock, String skuCode) {
        this.isInStock = isInStock;
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
