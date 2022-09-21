package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;
@Repository("accountDAO")
public class AccountDAOImpl implements IAccountDAO {

    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils connectionUtils;

    @Override
    public List<Account> findAllAccounts() {
        try {
            return runner.query(connectionUtils.getThreadConnection(),
                    "select * from account",
                    new BeanListHandler<>(Account.class));
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnection(),
                    "select * from account where id = ?", new BeanHandler<>(Account.class), accountId);
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),
                    "insert into account(name, money) values (?, ?)",
                    account.getName(), account.getMoney());
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnection(),
                    "update account set name = ?, money = ? where id = ?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update(connectionUtils.getThreadConnection(),"delete from account where id = ?", accountId);
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getThreadConnection(),
                    "select * from account where name = ?",
                    new BeanListHandler<>(Account.class),
                    accountName);
            if (CollectionUtils.isEmpty(accounts))
                return null;
            if (accounts.size() > 1)
                throw new RuntimeException("结果集不唯一，数据有问题");
            return accounts.get(0);
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
