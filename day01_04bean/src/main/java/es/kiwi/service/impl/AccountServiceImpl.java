package es.kiwi.service.impl;


import es.kiwi.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    /*如果没有默认构造函数，第一种bean构造方式会报错 Failed to instantiate [es.kiwi.service.impl.AccountServiceImpl]: No default constructor found;*/
    /*public AccountServiceImpl(String name) {
        System.out.println("对象创建了");
    }*/

    @Override
    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了......");
    }

    /*bean对象的生命周期相关*/
    public void init() {
        System.out.println("对象初始化了...");
    }

    public void destroy() {
        System.out.println("对象销毁了...");
    }
}
