package com.shopwell;

public enum PRODUCTCATEGORY {
    ELECTRONICS, BEVERAGES, GROCERIES, TOILETRIES;

    @Override
    public String toString() {
        switch(this) {
            case GROCERIES: return "Groceries";
            case TOILETRIES: return "Toiletries";
            case BEVERAGES: return "Beverages";
            case ELECTRONICS: return "Electronics";
            default: return "Not Available";
        }
    }
}
