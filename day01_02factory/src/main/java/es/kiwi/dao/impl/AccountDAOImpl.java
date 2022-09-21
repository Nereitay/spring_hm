package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;

/**
 * 账户的持久层实现类
 */
public class AccountDAOImpl implements IAccountDAO {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
