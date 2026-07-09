package com.cognizant.inventory.model;

public class InventoryItem {
    private Long productId;
    private String productName;
    private Integer quantityInStock;
    private String warehouseLocation;

    public InventoryItem() {}

    public InventoryItem(Long productId, String productName, Integer quantityInStock, String warehouseLocation) {
        this.productId = productId;
        this.productName = productName;
        this.quantityInStock = quantityInStock;
        this.warehouseLocation = warehouseLocation;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(Integer quantityInStock) { this.quantityInStock = quantityInStock; }
    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }
}
