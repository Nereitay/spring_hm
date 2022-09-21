package es.kiwi.test;

import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */
public class AccountServiceTest {

    private IAccountService as;

    @Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        as = ac.getBean("accountService", IAccountService.class);
    }

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
        Account account = as.findAccountById(4);
        account.setMoney(456789f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        as.deleteAccount(5);
    }

}
