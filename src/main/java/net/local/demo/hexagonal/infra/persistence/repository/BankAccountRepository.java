package net.local.demo.hexagonal.infra.persistence.repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import net.local.demo.hexagonal.application.domain.entities.Account;
import net.local.demo.hexagonal.application.ports.outgoing.LoadAccountPort;
import net.local.demo.hexagonal.application.ports.outgoing.SaveAccountPort;
import net.local.demo.hexagonal.infra.mappers.AccountMapper;
import net.local.demo.hexagonal.infra.persistence.entities.AccountJPA;
import net.local.demo.hexagonal.infra.persistence.repository.jpa.JpaRepository;

@ApplicationScoped
public class BankAccountRepository extends JpaRepository<AccountJPA, Long> implements LoadAccountPort, SaveAccountPort {

    public BankAccountRepository() {
        super(AccountJPA.class, null);
    }

    @Inject
    public BankAccountRepository(EntityManager entityManager) {
        super(AccountJPA.class, entityManager);
    }

    @Override @Transactional
    public void save(Account account) {
        persist(AccountMapper.fromAccount(account));
    }

    @Override
    public Optional<Account> load(Long id) {
        var accountJPA = loadById(id);
        if(accountJPA.isPresent()) {
            return Optional.of(AccountMapper.fromAccountJPA(accountJPA.get()));
        }
        return Optional.empty();
    }    
}
