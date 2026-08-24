package com.hdfc.strategy;

public class NoDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double amount) {
        return amount;
    }
}
