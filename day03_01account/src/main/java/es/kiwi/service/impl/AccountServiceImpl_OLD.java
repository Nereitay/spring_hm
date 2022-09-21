package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import es.kiwi.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务实现层实现类
 *
 * 事务控制应该都是在业务层
 */
public class AccountServiceImpl_OLD implements IAccountService {

    private IAccountDAO accountDAO;

    private TransactionManager txManager;

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Override
    public List<Account> findAllAccounts() {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts = accountDAO.findAllAccounts();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            txManager.release();
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account = accountDAO.findAccountById(accountId);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            txManager.release();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDAO.saveAccount(account);
            //3.提交事务
            txManager.commit();

        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            txManager.release();
        }


    }

    @Override
    public void updateAccount(Account account) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDAO.updateAccount(account);
            //3.提交事务
            txManager.commit();

        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            txManager.release();
        }


    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDAO.deleteAccount(accountId);
            //3.提交事务
            txManager.commit();

        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            //6.释放连接
            txManager.release();
        }

    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //1.开启事务
            txManager.beginTransaction();

            //2.执行操作
            //2.1根据名称查询转出账户
            Account source = accountDAO.findAccountByName(sourceName);
            //2.2根据名称查询转入账户
            Account target = accountDAO.findAccountByName(targetName);
            //2.3转出账户减钱
            source.setMoney(source.getMoney() - money);
            //2.4转入账户加钱
            target.setMoney(target.getMoney() + money);
            //2.5更新转出账户
            accountDAO.updateAccount(source);

            int i = 1 / 0;

            //2.6更新转入账户
            accountDAO.updateAccount(target);

            //3.提交事务
            txManager.commit();
        } catch (Exception e) {
            //4.回滚操作
            txManager.rollback();
            e.printStackTrace();
        } finally {
            //5.释放连接
            txManager.release();
        }
    }


}
