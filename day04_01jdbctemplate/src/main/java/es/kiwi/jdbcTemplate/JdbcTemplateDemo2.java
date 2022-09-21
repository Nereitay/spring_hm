package es.kiwi.jdbcTemplate;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * JdbcTemplate最基本用法
 */
public class JdbcTemplateDemo2 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDAO accountDAO = ac.getBean("accountDAO", IAccountDAO.class);
        Account a = accountDAO.findAccountById(1);
        System.out.println(a);

        a.setMoney(1000f);
        accountDAO.updateAccount(a);
        System.out.println(accountDAO.findAccountById(1));

        IAccountDAO accountDAO2 = ac.getBean("accountDAO2", IAccountDAO.class);
        Account a2 = accountDAO2.findAccountById(1);
        System.out.println(a2);

    }
}
