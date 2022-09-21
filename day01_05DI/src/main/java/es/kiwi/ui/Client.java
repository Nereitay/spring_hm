package es.kiwi.ui;

import es.kiwi.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*构造方法注入*/
        getServiceBean("accountService");

        /*Setter方法注入*/
        getServiceBean("accountService2");

        /*复杂对象注入*/
        getServiceBean("accountService3");
    }

    private static void getServiceBean(String beanName) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean(beanName);
        as.saveAccount();
    }

}
