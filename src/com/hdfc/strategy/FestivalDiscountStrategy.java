package com.hdfc.strategy;

public class FestivalDiscountStrategy implements DiscountStrategy{

    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90;
    }
}
