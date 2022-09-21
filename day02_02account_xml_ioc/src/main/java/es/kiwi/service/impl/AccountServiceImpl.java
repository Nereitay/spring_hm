package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;

import java.util.List;

public class AccountServiceImpl implements IAccountService {

    private IAccountDAO accountDAO;

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

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
