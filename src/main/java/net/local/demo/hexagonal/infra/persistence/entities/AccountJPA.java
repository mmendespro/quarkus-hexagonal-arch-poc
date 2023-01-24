package net.local.demo.hexagonal.infra.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountJPA implements Serializable {
    
    @Id
    @Column(name = "PK_ACCOUNT", nullable = false)
    public Long accountId;

    @Column(name = "VL_BALANCE", nullable = false)
    public float balance;

    public AccountJPA() {
        super();
    }

    public AccountJPA(Long accountId, float balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    
}
