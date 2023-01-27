package net.local.demo.hexagonal.application.domain.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.local.demo.hexagonal.application.domain.valueObjects.Money;

public class Account {
    
    private Long id;
    private Money balance;
    private List<Transaction> transactions;

    public Account(Long id, float balance) {
        this.id = id;
        this.balance = new Money(balance);
        this.transactions = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void deposit(float value) {
        balance.increase(value);
        this.transactions.add(new Transaction("deposit", value));
    }
    
    public void withdraw(float value) {
        balance.decrease(value);
        this.transactions.add(new Transaction("withdraw", value));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
