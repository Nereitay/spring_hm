package es.kiwi.ui;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.service.IAccountService;
import es.kiwi.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*用于对象创建的注解 @Service @Repository @Controller @Component*/
//        creation();

//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        /*IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();

        IAccountService as2 = (IAccountService) ac.getBean("accountService");
        System.out.println(as == as2);

        ac.close();*/
        IAccountService as = (IAccountService) ac.getBean("accountService");
    }

    private static void creation() {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        //2.根据id获取Bean对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        System.out.println(as);

        IAccountDAO ad = ac.getBean("accountDAO", IAccountDAO.class);
        System.out.println(ad);
    }

}
