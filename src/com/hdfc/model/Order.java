package com.hdfc.model;

import com.hdfc.strategy.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
    private Customer customer;
    private List<OrderItem> items;

    private DiscountStrategy strategy;

    private String shippingAddress;
    private String paymentMode;
    private String couponCode;
    private boolean giftWrap;

    private Order(OrderBuilder builder) {

        this.orderId = builder.orderId;
        this.customer = builder.customer;
        this.items = new ArrayList<>();

        this.shippingAddress = builder.shippingAddress;
        this.paymentMode = builder.paymentMode;
        this.couponCode = builder.couponCode;
        this.giftWrap = builder.giftWrap;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public boolean isGiftWrap() {
        return giftWrap;
    }

    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateTotal() {

        double total = 0;

        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public double calculateFinalAmount() {

        double total = calculateTotal();

        if (strategy == null) {
            return total;
        }

        return strategy.applyDiscount(total);
    }

    public void printSummary() {

        System.out.println("Order Summary");
        System.out.println("========================");

        System.out.println("Order Id: " + orderId);
        System.out.println("Customer: " + customer.getName());

        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Payment Mode: " + paymentMode);
        System.out.println("Coupon Code: " + couponCode);
        System.out.println("Gift Wrap: " + giftWrap);

        System.out.println();
        System.out.println("Items:");
        System.out.println("========================");

        items.forEach(System.out::println);

        System.out.println();
        System.out.println("Total: Rs: " + calculateTotal());
        System.out.println("Final Amount: Rs: " + calculateFinalAmount());
    }

    @Override
    public String toString() {

        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", items=" + items +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", giftWrap=" + giftWrap +
                '}';
    }

    public static class OrderBuilder {

        private int orderId;
        private Customer customer;

        private String shippingAddress;
        private String paymentMode;
        private String couponCode;
        private boolean giftWrap;

        public OrderBuilder orderId(int orderId) {

            this.orderId = orderId;
            return this;
        }

        public OrderBuilder customer(Customer customer) {

            this.customer = customer;
            return this;
        }

        public OrderBuilder shippingAddress(String shippingAddress) {

            this.shippingAddress = shippingAddress;
            return this;
        }

        public OrderBuilder paymentMode(String paymentMode) {

            this.paymentMode = paymentMode;
            return this;
        }

        public OrderBuilder couponCode(String couponCode) {

            this.couponCode = couponCode;
            return this;
        }

        public OrderBuilder giftWrap(boolean giftWrap) {

            this.giftWrap = giftWrap;
            return this;
        }

        public Order build() {

            if (orderId <= 0) {
                throw new IllegalStateException("Invalid Order Id");
            }

            if (customer == null) {
                throw new IllegalStateException("Customer is required");
            }

            return new Order(this);
        }
    }
}