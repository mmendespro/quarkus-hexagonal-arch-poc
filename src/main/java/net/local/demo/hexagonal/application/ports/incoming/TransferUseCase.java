package net.local.demo.hexagonal.application.ports.incoming;

public interface TransferUseCase {
    void transfer(Long fromAccountId, Long toAccountId, float amount);
}
