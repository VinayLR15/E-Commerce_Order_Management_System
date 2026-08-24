package com.hdfc.model;

public class BookProduct extends Product{

    public BookProduct(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getCategory() {
        return "Book";
    }
}
