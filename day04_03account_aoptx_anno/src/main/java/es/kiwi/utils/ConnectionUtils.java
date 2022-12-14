package es.kiwi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
@Component("connectionUtils")
public class ConnectionUtils {

    private ThreadLocal<Connection> t1 = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection() {

        //1. 先从ThreadLocal上获取
        Connection conn = t1.get();

        try {
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3. 从数据源中获取一个连接，并且存入 ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        t1.remove();
    }
}
