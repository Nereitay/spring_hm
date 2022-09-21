package es.kiwi.service;

import es.kiwi.domain.Account;

public interface IAccountService {

    Account findAccountById(Integer accountId);

    void transfer(String sourceName, String targetName, Float money);
}
