package net.local.demo.hexagonal.application.domain.entities;

import java.time.LocalDate;

public class Transaction {

    private String description;
    private float amount;
    private final LocalDate date;
    
    public Transaction(String description, float amount) {
        this.description = description;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
