package com.isolsgroup.demo.model;

public class CartItem {
    private HomeResponse product;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public HomeResponse getProduct() {
        return product;
    }
    public void setProduct(HomeResponse product) {
        this.product = product;
    }
}
