package com.hdfc.model;

public class ElectronicsProduct extends Product {

    public ElectronicsProduct(int productId, String productName, double price) {
        super(productId, productName, price);
    }

    @Override
    public String getCategory() {
        return "Electronic";
    }
}
