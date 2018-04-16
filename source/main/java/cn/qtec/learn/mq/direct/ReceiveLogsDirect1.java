package cn.qtec.learn.mq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class ReceiveLogsDirect1 {
    public static final String EXCHANGE_NAME="direct_name";
    public static final String[] routingKeys = new String[]{"info","warning"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        String queueName = channel.queueDeclare().getQueue();
        for (String routingKey : routingKeys) {
                channel.queueBind(queueName,EXCHANGE_NAME,routingKey);
            System.out.println("ReceiveLogDirect1 exchange:" + EXCHANGE_NAME + ",  queue is " + queueName + ", BindRoudingKey: " + routingKey);
        }
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = " ReceiveLogsDirect1 Received ' "+envelope.getRoutingKey() +" :' " +new String(body,"UTF-8") + "'";
                System.out.println(message);
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
