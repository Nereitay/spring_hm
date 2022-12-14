package es.kiwi.dao;

import es.kiwi.domain.Account;

import java.util.List;

/**
 * 账户持久层接口
 */
public interface IAccountDAO {

    /**
     * 查询所有
     * @return
     */
    List<Account> findAllAccounts();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 保存
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */
    void deleteAccount(Integer accountId);
}
