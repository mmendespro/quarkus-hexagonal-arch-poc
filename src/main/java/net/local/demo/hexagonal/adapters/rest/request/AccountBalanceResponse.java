package net.local.demo.hexagonal.adapters.rest.request;

import java.util.List;

import net.local.demo.hexagonal.application.domain.entities.Transaction;

public class AccountBalanceResponse {
    
    private float balance;
    private List<Transaction> transactions;

    public AccountBalanceResponse(float balance, List<Transaction> transactions) {
        this.balance = balance;
        this.transactions = transactions;
    }

    public float getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
