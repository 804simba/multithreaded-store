package com.shopwell.staff;

import com.shopwell.customers.Customer;
import com.shopwell.products.Product;
import com.shopwell.Store;
import com.shopwell.enums.Role;
import com.shopwell.services.ICashierService;
import com.shopwell.services.IQueueManager;
import com.shopwell.utilities.CartSizeComparator;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class Cashier extends Staff implements Runnable, IQueueManager {
    private boolean employmentStatus;
    private Queue<Customer> customerQueue;
    private final Object lock = new Object();

    public Cashier(String name, Role role, Store store) {
        super(name, role, store);
        this.employmentStatus = true;
        this.customerQueue = new PriorityQueue<>(Comparator.comparing(Customer::getTimeOfArrival));
    }
    public Cashier(String name, Role role, Store store, CartSizeComparator cartSizeComparator) {
        super(name, role, store);
        this.employmentStatus = true;
        this.customerQueue = new PriorityQueue<>(cartSizeComparator);
    }
    public synchronized boolean checkOutCustomer(Customer customer) {
        try {
            Thread.sleep(5000);
            double totalCost = 0.0;
            List<Product> customerCart = customer.getCart();

            for (Product cartItem : customerCart) {
                if (store.isAvailable(cartItem)) {
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
                System.out.println("Thank you...");
                return true;
            } else {
                System.out.println("Insufficient funds.");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean updateProductQuantity(Product product, int quantity) {
        for (Product productInStock : store.getProductsList()) {
            if (productInStock.getProductName().equals(product.getProductName())) {
                productInStock.setProductQuantity(productInStock.getProductQuantity() - quantity);
                return true;
            }
        }
        return false;
    }
    private void updateCompanyBalance(double amount) {
        store.setDailySalesAccount(store.getDailySalesAccount() + amount, this);
    }

    public void issueReceipt(Customer customer, double totalPrice) {
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

    @Override
    public synchronized void addCustomersToQueue(Queue<Customer> queue) {
        customerQueue.addAll(queue);
        System.out.println("Customers on the queue: " + this.getCustomerQueue());
    }
    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                String name = Thread.currentThread().getName();
                System.out.printf("%s is working...\n", name);
                while(customerQueue.isEmpty() && employmentStatus) {
                    try {
                        System.out.println(name + "'s customer queue is empty, waiting for arrival of customers...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                if (!employmentStatus) {
                    break;
                }
                Customer nextCustomer = customerQueue.poll();
                assert nextCustomer != null;
                checkOutCustomer(nextCustomer);
                System.out.println(name + "'s Queue: " + customerQueue);
                String s = String.format("%s Attending to %s\n", name, nextCustomer.getName());
                System.out.printf(s);
            }
        }
    }
    public void startCashierThread() {
        Thread t = new Thread(this, this.getName());
        if (t.getState() == Thread.State.NEW) {
            t.start();
            System.out.println(t.getName() + " thread has started...");
        } else {
            throw new IllegalMonitorStateException("Thread is already running..");
        }
    }
}
