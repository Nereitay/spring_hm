package es.kiwi.service.impl;

import es.kiwi.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    @Override
    public void saveAccount() {
        System.out.println("执行了保存");
        int i = 1 / 0;
    }

    @Override
    public void updateAccount(int i) {
        System.out.println("执行了更新" + i);

    }

    @Override
    public int deleteAccount() {
        System.out.println("执行了删除");

        return 0;
    }
}
