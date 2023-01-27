package net.local.demo.hexagonal.application.ports.incoming;

import net.local.demo.hexagonal.application.domain.entities.Account;

public interface CheckBalanceUseCase {
    Account checkBalance(Long id);
}
