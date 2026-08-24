package com.hdfc;

import com.hdfc.factory.ProductFactory;
import com.hdfc.model.Customer;
import com.hdfc.model.Order;
import com.hdfc.model.OrderItem;
import com.hdfc.model.Product;
import com.hdfc.strategy.PremiumDiscountStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {

        List<Product> products = List.of(

                ProductFactory.createProduct(
                        "electronics",
                        1,
                        "Laptop",
                        75000
                ),

                ProductFactory.createProduct(
                        "electronics",
                        2,
                        "Mouse",
                        1500
                ),

                ProductFactory.createProduct(
                        "electronics",
                        3,
                        "Keyboard",
                        2500
                ),

                ProductFactory.createProduct(
                        "electronics",
                        4,
                        "Monitor",
                        18000
                ),

                ProductFactory.createProduct(
                        "book",
                        5,
                        "Java Book",
                        900
                ),

                ProductFactory.createProduct(
                        "book",
                        6,
                        "Spring Boot Book",
                        1200
                ),

                ProductFactory.createProduct(
                        "grocery",
                        7,
                        "Rice",
                        1000
                ),

                ProductFactory.createProduct(
                        "grocery",
                        8,
                        "Sugar",
                        500
                ),

                ProductFactory.createProduct(
                        "grocery",
                        9,
                        "Milk",
                        60
                ),

                ProductFactory.createProduct(
                        "grocery",
                        10,
                        "Oil",
                        250
                )
        );

        System.out.println("All Products:");
        System.out.println("========================");

        products.stream()
                .map(Product::getName)
                .forEach(System.out::println);

        Customer customer =
                new Customer(1, "Vinay", "vinay@gmail.com");

        Product laptop = products.get(0);
        Product mouse = products.get(1);

        OrderItem item1 =
                new OrderItem(laptop, 1);

        OrderItem item2 =
                new OrderItem(mouse, 2);

        Order order = new Order.OrderBuilder()
                .orderId(101)
                .customer(customer)
                .shippingAddress("Kolar")
                .paymentMode("UPI")
                .couponCode("SALE20")
                .giftWrap(true)
                .build();

        order.addItem(item1);
        order.addItem(item2);

        order.setStrategy(new PremiumDiscountStrategy());

        System.out.println();
        System.out.println(order);

        System.out.println();
        order.printSummary();

        System.out.println();

        Map<String, List<Product>> groupedProducts =products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));


        groupedProducts.forEach(
                (category, list) -> {

                    System.out.println(
                            category + " -> " + list
                    );
                }
        );

        System.out.println();

        Product expensiveProduct =products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);

        System.out.println("Expensive Product: " + expensiveProduct);

    }
}