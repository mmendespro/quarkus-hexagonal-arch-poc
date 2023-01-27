package net.local.demo.hexagonal.infra.mappers;

import net.local.demo.hexagonal.application.domain.entities.Account;
import net.local.demo.hexagonal.infra.persistence.entities.AccountJPA;

public class AccountMapper {
    
    public static Account fromAccountJPA(AccountJPA accountJPA) {
        return new Account(accountJPA.accountId, accountJPA.balance);
    }

    public static AccountJPA fromAccount(Account account) {
        return new AccountJPA(account.getId(), account.getBalance().getValue());
    }
}
