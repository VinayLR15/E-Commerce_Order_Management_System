package com.hdfc.factory;

import com.hdfc.exception.ProductNotFoundException;
import com.hdfc.model.BookProduct;
import com.hdfc.model.ElectronicsProduct;
import com.hdfc.model.GroceryProduct;
import com.hdfc.model.Product;

public class ProductFactory {

    public static Product createProduct(
            String type,
            int id,
            String name,
            double price) {

        if (type == null) {
            throw new IllegalArgumentException(
                    "Product type cannot be null");
        }

        switch (type.toLowerCase()) {

            case "electronics":
                return new ElectronicsProduct(id, name, price);

            case "book":
                return new BookProduct(id, name, price);

            case "grocery":
                return new GroceryProduct(id, name, price);

            default:
                throw new ProductNotFoundException(
                        "Invalid Product Type");
        }
    }
}