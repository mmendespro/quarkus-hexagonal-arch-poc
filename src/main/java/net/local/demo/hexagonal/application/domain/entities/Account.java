package net.local.demo.hexagonal.application.domain.entities;

import net.local.demo.hexagonal.application.domain.valueObjects.Money;

public class Account {
    
    private Long id;
    private Money balance;

    public Account(Long id, float balance) {
        this.id = id;
        this.balance = new Money(balance);
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
    }
    
    public void withdraw(float value) {
        balance.decrease(value);
    }
}
