package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 *
 */
@Repository("accountDAO1")
public class AccountDAOImpl implements IAccountDAO {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户111111111111111");
    }
}
