package es.kiwi.dao.impl;

import es.kiwi.dao.IAccountDAO;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 *
 */
@Repository("accountDAO2")
public class AccountDAOImpl2 implements IAccountDAO {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户2222222222222222222");
    }
}
