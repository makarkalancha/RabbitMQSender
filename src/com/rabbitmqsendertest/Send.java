package com.rabbitmqsendertest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by Makar Kalancha
 * Date: 22 Nov 2016
 * Time: 15:06
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");

            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "Hello World!";
            for (int i = 0; i < 5; i++) {
                String tmp = message + i;
                channel.basicPublish("", QUEUE_NAME, null, tmp.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + tmp + "'");
            }
        }finally {
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        }
    }
}
