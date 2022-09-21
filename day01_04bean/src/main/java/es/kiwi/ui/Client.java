package es.kiwi.ui;

import es.kiwi.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        /*bean的三种创建方式*/
//        beanCreation();

        /*bean的作用范围*/
//        beanScope();

        /* bean对象生命周期*/
        //1.获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();

        //手动关闭容器
        ac.close();

    }

    private static void beanScope() {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        IAccountService as1 = (IAccountService) ac.getBean("accountService");
        IAccountService as2 = (IAccountService) ac.getBean("accountService");

        System.out.println(as1 == as2);
    }

    private static void beanCreation() {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();
    }


}
