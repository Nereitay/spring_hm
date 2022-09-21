package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Override
    public List<Account> findAllAccounts() {
        return accountDAO.findAllAccounts();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDAO.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDAO.saveAccount(account);

    }

    @Override
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);

    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDAO.deleteAccount(accountId);
    }
}
