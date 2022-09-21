package es.kiwi.service.impl;

import es.kiwi.dao.IAccountDAO;
import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDAO.findAccountById(accountId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void transfer(String sourceName, String targetName, Float money) {

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

//        int i = 1 / 0;

        //2.6更新转入账户
        accountDAO.updateAccount(target);
    }
}
