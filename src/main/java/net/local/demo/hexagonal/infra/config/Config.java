package net.local.demo.hexagonal.infra.config;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import net.local.demo.hexagonal.application.services.BankAccountService;
import net.local.demo.hexagonal.infra.persistence.repository.BankAccountRepository;

@Dependent
public class Config {

    @Default
    public BankAccountService bankAccountService(BankAccountRepository repository){
        return new BankAccountService(repository, repository);
    }
}