package com.stardew.model;

public class SellableDTO {

    private final int quantity;
    private final String name;
    private final int price;

    public SellableDTO(int quantity, String name, int price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
