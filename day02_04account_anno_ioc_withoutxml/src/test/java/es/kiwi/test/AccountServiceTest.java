package es.kiwi.test;

import config.JdbcConfig;
import config.SpringConfiguration;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 *
 * Spring整合Junit分析
 * 1、应用程序的入口
 * 	main方法
 * 2、junit单元测试中，没有main方法也能执行
 * 	junit集成了一个main方法
 * 	该方法就会判断当前测试类中哪些方法有 @Test注解
 * 	junit就让有Test注解的方法执行
 * 3、junit不会管我们是否采用spring框架
 * 	在执行测试方法时，junit根本不知道我们是不是使用了spring框架
 * 	所以也就不会为我们读取配置文件/配置类创建spring核心容器
 * 4、由以上三点可知
 * 	当测试方法执行时，没有Ioc容器，就算写了Autowired注解，也无法实现注入
 *
 * Spring整合junit的配置
 *      1、导入spring整合junit的jar(坐标)
 *      2、使用Junit提供的一个注解把原有的main方法替换了，替换成spring提供的
 *             @Runwith
 *      3、告知spring的运行器，spring和ioc创建是基于xml还是注解的，并且说明位置
 *          @ContextConfiguration
 *                  locations：指定xml文件的位置，加上classpath关键字，表示在类路径下
 *                  classes：指定注解类所在地位置
 *
 *   当我们使用spring 5.x版本的时候，要求junit的jar必须是4.12及以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService as;

    /*private ApplicationContext ac;

    @Before
    public void init() {
//      ac = new AnnotationConfigApplicationContext(SpringConfiguration.class, JdbcConfig.class);
        ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        as = ac.getBean("accountService", IAccountService.class);
    }*/

    @Test
    public void testFindAll() {
        List<Account> accounts =  as.findAllAccounts();
        accounts.forEach(System.out::println);
    }

    @Test
    public void testFindOne() {
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        Account account = as.findAccountById(6);
        account.setMoney(456789f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(5);
    }

}
