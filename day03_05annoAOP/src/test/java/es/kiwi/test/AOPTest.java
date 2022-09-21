package es.kiwi.test;

import config.SpringConfiguration;
import es.kiwi.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试AOP配置
 */
public class AOPTest {

    public static void main(String[] args) {
//        xml();

        anno();
    }

    private static void anno() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.saveAccount();
    }

    private static void xml() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        as.saveAccount();
    }
}
