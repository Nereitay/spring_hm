package es.kiwi.dao;

import es.kiwi.domain.Account;

public interface IAccountDAO {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountName);

    void updateAccount(Account account);
}
