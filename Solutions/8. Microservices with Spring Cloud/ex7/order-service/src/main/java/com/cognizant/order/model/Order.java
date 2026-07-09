package com.cognizant.order.model;

public class Order {
    private Long id;
    private Long userId;
    private String product;
    private Double amount;
    private String status;

    public Order() {}

    public Order(Long id, Long userId, String product, Double amount, String status) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
