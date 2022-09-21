package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl implements IAccountDAO {

    private QueryRunner runner;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    @Override
    public List<Account> findAllAccounts() {
        try {
            return runner.query("select * from account", new BeanListHandler<>(Account.class));
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query("select * from account where id = ?", new BeanHandler<>(Account.class), accountId);
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name, money) values (?, ?)", account.getName(), account.getMoney());
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update("update account set name = ?, money = ? where id = ?",
                    account.getName(), account.getMoney(), account.getId());
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from account where id = ?", accountId);
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
