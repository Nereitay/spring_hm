package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements IAccountService {

    private IAccountDAO accountDAO;

    private TransactionTemplate transactionTemplate;

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    /*
     * 每个方法都要这么写
     */
    @Override
    public Account findAccountById(Integer accountId) {

        return transactionTemplate.execute(new TransactionCallback<Account>() {

            @Override
            public Account doInTransaction(TransactionStatus status) {
                return accountDAO.findAccountById(accountId);
            }
        });

    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {

        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                //2.执行操作
                System.out.println("transfer.......");
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

//                int i = 1 / 0;

                //2.6更新转入账户
                accountDAO.updateAccount(target);
                return null;
            }
        });


    }
}
