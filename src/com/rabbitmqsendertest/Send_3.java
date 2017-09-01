/*
 * Software property of Acquisio. Copyright 2003-2017.
 */
package com.rabbitmqsendertest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Macar Calancea (mcalancea@acquisio.com)
 */
public class Send_3 {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "Hello";

        for (int i = 0; i < 5; i++) {
            String tmp = message + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, tmp.getBytes());
            System.out.println(" [x] Sent '" + tmp + "'");
        }

        channel.close();
        connection.close();
    }

}
