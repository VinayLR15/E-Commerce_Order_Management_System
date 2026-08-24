package com.hdfc.strategy;

public class StudentDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.15;
    }
}
