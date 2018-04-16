package cn.qtec.learn.mq.p2p;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class Producer {
    public final static String QUEUE_NAME="rabbitmq.test";
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    public static void main(String[] args) throws IOException, TimeoutException, BrokenBarrierException, InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "hello rabbitmq";
        Thread thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        for (int i1 = 0; i1 < 100; i1++) {
                            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
                        }
                        Thread.sleep(100);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("发送完毕");
        thread.start();
    }
}
