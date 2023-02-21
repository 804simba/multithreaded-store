package com.shopwell;

import com.shopwell.customers.Customer;
import com.shopwell.products.Product;
import com.shopwell.staff.Cashier;
import com.shopwell.utilities.CustomerComparator;
import com.shopwell.utilities.ExcelManager;
import com.shopwell.staff.Manager;
import lombok.Getter;

import java.util.*;

@Getter
public class Store {
    private final String name;
    private Double accountBalance;
    private Double dailySalesAccount = 0.0;
    private final List<Cashier> cashiersList = new ArrayList<>();
    private final List<Product> productsList = new ArrayList<>();

    private final Queue<Customer> customerQueue;

    private ExcelManager excelManager;

    public Store(String name, Double accountBalance) {
        this.name = name;
        this.accountBalance = accountBalance;
        try {
            this.excelManager = new ExcelManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.customerQueue = new PriorityQueue<>();
    }

    public Store(String name, Double accountBalance, CustomerComparator cus) {
        this.name = name;
        this.accountBalance = accountBalance;
        try {
            this.excelManager = new ExcelManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.customerQueue = new PriorityQueue<>(10, cus);
    }

    public double checkAccountBalance(Object other) {
        if (other instanceof Manager) {
            return accountBalance;
        }
        return 0.0;
    }

    public void setDailySalesAccount(Double amount, Object other) {
        if (other instanceof Cashier) {
            dailySalesAccount += amount;
            return;
        }
        System.out.println("You don't have access to update this account.");
    }

    public void updateStoreAccountBalance(Double totalDailySales, Object other) {
        if (other instanceof Manager)
            accountBalance += totalDailySales;
    }

    public void addCashier(Cashier cashier, Object other) {
        if (other instanceof Manager)
            cashiersList.add(cashier);
    }

    public void addProducts(Product product, Object other) {
        if (other instanceof Manager)
            productsList.add(product);
    }

    public boolean isAvailable(Product product) {
        for (Product storeProduct : getProductsList()) {
            if (storeProduct.getProductName().equals(product.getProductName()) && storeProduct.getProductQuantity() >= product.getProductQuantity())
                return true;
        }
        return false;
    }

    public int ReadAllProductsInExcelSheet() {
        try {
            return excelManager.printAllDataFromExcel();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public void addProductToExcel(Product product) {
        excelManager.addProductToInventory(product);
    }

    public void updateProductQtyInExcel(Product product, int quantity) {
        excelManager.updateProductQuantity(product, quantity);
    }

    public void addCustomerToQueue(Customer customer) {
        customerQueue.offer(customer);
        String s = String.format("%s joined the queue with %d items in their cart at %s...\n", customer.getName(), customer.getCart().size(), customer.getTimeOfArrival().toString());
        System.out.println(s);
    }

    public void serveCustomersBasedOnFIFO(Cashier cashier) {
        Customer nextCustomer;
        while (!customerQueue.isEmpty()) {
            nextCustomer = customerQueue.poll();
            String s = String.format("Attending to %s\n", nextCustomer.getName());
            System.out.printf(s);
            cashier.checkOutCustomer(nextCustomer);
        }
    }

    public void serveCustomersBasedOnNumberOfItems(Cashier cashier) {
        Customer nextCustomer;
        while(!customerQueue.isEmpty()) {
            System.out.println("------------------>");
            nextCustomer = customerQueue.poll();
            System.out.println("Queue: " + customerQueue);
            String s = String.format("Attending to %s\n", nextCustomer.getName());
            System.out.printf(s);
            cashier.checkOutCustomer(nextCustomer);
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "productsList=" + productsList +
                '}';
    }
}
