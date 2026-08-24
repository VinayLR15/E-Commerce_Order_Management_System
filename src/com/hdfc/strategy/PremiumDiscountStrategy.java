package com.hdfc.strategy;

public class PremiumDiscountStrategy implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.80;
    }
}
