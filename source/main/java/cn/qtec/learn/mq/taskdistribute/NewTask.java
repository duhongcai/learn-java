package cn.qtec.learn.mq.taskdistribute;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by duhc on 2018/4/13.
 */
public class NewTask {
    private static final String QUEUE_NAME="task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> param = new HashMap<>();
        param.put("abc","edf");
        param.put("ddd","cdcd");
        channel.queueDeclare(QUEUE_NAME,true,false,false,param);
        int flag = 1;
        while (true){
            flag++;
            for (int i = 0; i < 100; i++) {
                String message = "message :version -- "+flag +" -- " +i;
                channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            }
        }
    }
}
