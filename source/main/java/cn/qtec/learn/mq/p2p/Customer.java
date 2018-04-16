package cn.qtec.learn.mq.p2p;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class Customer {
    public static final String QUEUE_NAME = "rabbitmq.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("Customer is Waiting Received messages");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"UTF-8");
                    try{
                        int a = 1/0;
                    }catch (Exception e){
                    }
                System.out.println("Consumer Received ' " + message + " ' ");
            }
        };
        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);
    }
}
