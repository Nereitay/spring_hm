package es.kiwi.factory;

import es.kiwi.domain.Account;
import es.kiwi.service.IAccountService;
import es.kiwi.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager txManager;

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    /**
     * 获取service代理对象
     * @return
     */
    public IAccountService getAccountService() {

        return (IAccountService) Proxy.newProxyInstance(
                accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        /*test方法只是joint point 而不是 point cut, 因为没有被增强*/
                        if ("test".equals(method.getName()))
                            return method.invoke(accountService, args);

                        Object rtValue = null;
                        try {
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            rtValue = method.invoke(accountService, args);
                            //3.提交事务
                            txManager.commit();
                            return rtValue;
                        }catch (Exception e){
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            //6.释放连接
                            txManager.release();
                        }

                    }
                });
    }
}
