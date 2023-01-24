package net.local.demo.hexagonal.application.services;

import java.util.NoSuchElementException;

import net.local.demo.hexagonal.application.domain.entities.Account;
import net.local.demo.hexagonal.application.exceptions.NoFundsException;
import net.local.demo.hexagonal.application.ports.incoming.CheckBalanceUseCase;
import net.local.demo.hexagonal.application.ports.incoming.CreateAccountUseCase;
import net.local.demo.hexagonal.application.ports.incoming.DepositUseCase;
import net.local.demo.hexagonal.application.ports.incoming.TransferUseCase;
import net.local.demo.hexagonal.application.ports.incoming.WithdrawUseCase;
import net.local.demo.hexagonal.application.ports.outgoing.LoadAccountPort;
import net.local.demo.hexagonal.application.ports.outgoing.SaveAccountPort;

public class BankAccountService implements CreateAccountUseCase, CheckBalanceUseCase, DepositUseCase, WithdrawUseCase, TransferUseCase {

    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    public BankAccountService(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public void withdraw(Long id, float amount) {
        if(this.checkBalance(id) < amount) {
            throw new NoFundsException(String.format("Account %s have no funds to this transaction.", id));
        }
        Account account = loadAccountPort.load(id).orElseThrow(NoSuchElementException::new);
        account.withdraw(amount);
        saveAccountPort.save(account);
    }

    @Override
    public void deposit(Long id, float amount) {
        Account account = loadAccountPort.load(id).orElseThrow(NoSuchElementException::new);
        account.deposit(amount);
        saveAccountPort.save(account);
    }

    @Override
    public float checkBalance(Long id) {
        Account account = loadAccountPort.load(id).orElseThrow(NoSuchElementException::new);
        return account.getBalance().getValue();
    }

    @Override
    public void create(Long id, float amount) {
        saveAccountPort.save(new Account(id, amount));
    }

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, float amount) {
        this.withdraw(fromAccountId, amount);
        this.deposit(toAccountId, amount);
    }
}
