package cn.qtec.learn.mq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class EmitLog {
    private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        for (int i = 0; i < 10; i++) {
            String message = "Hello world - "+i;
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println("EmitLog Send ' " + message);
        }
        channel.close();
        connection.close();
    }
}
