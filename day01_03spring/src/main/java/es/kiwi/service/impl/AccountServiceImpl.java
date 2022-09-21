package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.dao.impl.AccountDAOImpl;
import es.kiwi.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDAO accountDAO = new AccountDAOImpl();

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    @Override
    public void saveAccount() {
        accountDAO.saveAccount();
    }
}
