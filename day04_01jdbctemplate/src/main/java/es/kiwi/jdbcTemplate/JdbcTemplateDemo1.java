package es.kiwi.jdbcTemplate;

import es.kiwi.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate的最基本用法
 */

public class JdbcTemplateDemo1 {

    public static void main(String[] args) {
        /*手写注入*/
//        configJdbcTemplate();

        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
//        jt.execute("insert into account(name, money) VALUES ('ddd', 2000)");

        //保存
//        jt.update("insert into account(name, money) VALUES (?, ?)", "eee", 3000);

        //更新
//        jt.update("update account set name = ?, money = ? where id = ?", "test", 4567, 8);

        //删除
//        jt.update("delete from account where id = ?", 8);

        //查询
        /*List<Account> accounts = jt.query("select * from account where money > ?",
                new AccountRowMapper(), 1000f);*/

        /*List<Account> accounts = jt.query("select * from account where money > ?",
                new BeanPropertyRowMapper<>(Account.class), 1000f);
        accounts.forEach(System.out :: println);*/

        /*List<Account> accounts = jt.query("select * from account where id = ?",
                new BeanPropertyRowMapper<>(Account.class), 1);
        System.out.println(accounts.isEmpty() ? "没有内容" : accounts.get(0));*/

        //查询返回一行一列（使用聚合函数，但不加group by 子句)
        Long count = jt.queryForObject("select count(*) from account where money > ?", Long.class, 1000f);
        System.out.println(count);

    }

    private static void configJdbcTemplate() {
        //准备数据源：spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql:///spring_heima");
        ds.setUsername("root");
        ds.setPassword("root");

        //1.创建jdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate(ds);
        //给jt设置数据源
//        jt.setDataSource(ds);

        //2.执行操作
        jt.execute("insert into account(name, money) VALUES ('ccc', 1000)");
    }
}

class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
