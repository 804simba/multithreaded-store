package com.shopwell.services;

public interface IAccountManager {
    double checkAccountBalance(Object other);
    void setDailySalesAccount(Double amount, Object other);
    void updateStoreAccountBalance(Double totalDailySales, Object other);
}
