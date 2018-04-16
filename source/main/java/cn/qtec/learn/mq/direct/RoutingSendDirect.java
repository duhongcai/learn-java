package cn.qtec.learn.mq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class RoutingSendDirect {
    public static final String EXCHANGE_NAME = "direct_logs";
    public static final String[] routingKeys = new String[]{"info","warning","error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory =new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        for (String routingKey : routingKeys) {
            String message = "RoutingSendDirect Send the message level: "+routingKey;
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes());
            System.out.println("RoutingSendDirect Send " + routingKey + " ': " + message);
        }
        channel.close();
        connection.close();
    }
}
