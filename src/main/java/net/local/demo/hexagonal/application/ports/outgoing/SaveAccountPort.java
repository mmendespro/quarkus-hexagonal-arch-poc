package net.local.demo.hexagonal.application.ports.outgoing;

import net.local.demo.hexagonal.application.domain.entities.Account;

public interface SaveAccountPort {
    void save(Account account);
}
