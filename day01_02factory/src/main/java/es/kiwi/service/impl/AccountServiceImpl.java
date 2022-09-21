package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.dao.impl.AccountDAOImpl;
import es.kiwi.factory.BeanFactory;
import es.kiwi.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    /*两种创建对象的方式：*/

//    private IAccountDAO accountDAO = new AccountDAOImpl();

    /*IoC 控制权交给工厂或框架，削减计算机程序的耦合*/
    private IAccountDAO accountDAO = (IAccountDAO) BeanFactory.getBean("accountDao");

//    private int i = 1;


    @Override
    public void saveAccount() {
        int i = 1;
        accountDAO.saveAccount();
        System.out.println(i);
        i++;
    }
}
