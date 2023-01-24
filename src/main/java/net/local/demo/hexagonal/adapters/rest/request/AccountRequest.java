package net.local.demo.hexagonal.adapters.rest.request;

public class AccountRequest {
    
    private final Long id;
    private final float balance;

    public AccountRequest(Long id, float balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

}
