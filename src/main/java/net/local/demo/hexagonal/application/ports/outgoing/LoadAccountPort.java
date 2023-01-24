package net.local.demo.hexagonal.application.ports.outgoing;

import java.util.Optional;

import net.local.demo.hexagonal.application.domain.entities.Account;

public interface LoadAccountPort {
    Optional<Account> load(Long id);
}
