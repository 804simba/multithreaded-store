package com.shopwell.enums;

public enum ProductCategory {
    ELECTRONICS, BEVERAGES, GROCERIES, TOILETRIES;

    @Override
    public String toString() {
        return switch (this) {
            case GROCERIES -> "Groceries";
            case TOILETRIES -> "Toiletries";
            case BEVERAGES -> "Beverages";
            case ELECTRONICS -> "Electronics";
            default -> "Not Available";
        };
    }
}
