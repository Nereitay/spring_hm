package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 解决重复代码
 */
public class AccountDAOImpl2 extends JdbcDAOSupport implements IAccountDAO {

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accounts = getJdbcTemplate().query("select * from account where name = ?",
                new BeanPropertyRowMapper<>(Account.class), accountName);

        if (accounts.isEmpty())
            return null;
        if (accounts.size() > 1)
            throw new RuntimeException("结果集不唯一");

        return accounts.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        getJdbcTemplate().update("update account set name = ?, money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId());
    }
}
