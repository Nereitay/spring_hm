package es.kiwi.ui;

import es.kiwi.factory.BeanFactory;
import es.kiwi.service.IAccountService;
import es.kiwi.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {

        /*改造前*/
//        before();

        /*使用工厂模式解耦*/
//        solution();

        /*改造成单例模式*/
        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveAccount();
        }
    }

    private static void solution() {
        IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
        System.out.println(as);
        as.saveAccount();
    }

    private static void before() {
        IAccountService as = new AccountServiceImpl();
        as.saveAccount();

    }

}
