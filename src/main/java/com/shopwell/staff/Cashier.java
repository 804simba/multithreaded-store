package com.shopwell.staff;

import com.shopwell.Customer;
import com.shopwell.Product;
import com.shopwell.Store;
import lombok.Getter;
import lombok.Setter;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

@Getter @Setter
public class Cashier extends Staff {
    private boolean employmentStatus;

    public Cashier(String name, Designation designation, Store store) {
        super(name, designation, store);
        this.employmentStatus = false;
    }

    public boolean getEmploymentStatus() {
        return employmentStatus;
    }

    public void checkOutCustomer(Customer customer) {
        double totalCost = 0.0;
        List<Product> customerCart = customer.getCart();
        Iterator i = customerCart.iterator();

        while(i.hasNext()) {
            Product cartItem = (Product) i.next();
            if (store.isAvailable(cartItem) == true) {
                totalCost += cartItem.getProductPrice() * cartItem.getProductQuantity();
                updateProductQuantity(cartItem, cartItem.getProductQuantity());
            } else {
                customerCart.remove(cartItem);
                System.out.printf("%s is out of stock.\n", cartItem.getProductName());
                System.out.println();
            }
        }

        if (customer.getCreditCardBalance() >= totalCost) {
            customer.makePayment(totalCost);
            updateCompanyBalance(totalCost);
            issueReceipt(customer, totalCost);
            customer.getCart().clear();
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void updateProductQuantity(Product product, int quantity) {
        for (Product productInStock : store.getProductsList()) {
            if (productInStock.getProductName().equals(product.getProductName()))
                productInStock.setProductQuantity(productInStock.getProductQuantity() - quantity);
        }
    }


    private void updateCompanyBalance(double amount) {
        store.setDailySalesAccount(store.getDailySalesAccount() + amount, this);
    }

    public void issueReceipt(Customer customer, double total) {
        double totalPrice = total;
        List<Product> customerOrders = customer.getCart();
        int sNo = 0;
        System.out.println("<### RECEIPT ###>");
        System.out.println("S/No  Item  Quantity  Price");
        for (Product item : customerOrders) {
            System.out.printf("%d   %s   %d   %.2f\n", sNo, item.getProductName(), item.getProductQuantity(), item.getProductPrice());
            ++sNo;
        }
        System.out.printf("Total: %.2f\n", totalPrice);
        System.out.println("Thank you for your patronage.");
        System.out.println("<### See you next time!!! ###>");
    }
}
