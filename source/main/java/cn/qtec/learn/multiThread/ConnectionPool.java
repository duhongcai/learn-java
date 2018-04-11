package cn.qtec.learn.multiThread;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by duhc on 2018/4/11.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initSize) {
        if (initSize>0){
            for (int i = 0; i < initSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if (connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long millons) throws InterruptedException{
        synchronized (pool){
            if (millons<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + millons;
                long remaining = millons;
                while (pool.isEmpty() && remaining>0){
                    pool.wait(millons);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
