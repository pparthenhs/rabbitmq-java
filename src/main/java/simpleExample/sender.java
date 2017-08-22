package simpleExample;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by parthenis on 21/08/2017.
 */
public class sender {


	static String msg ="hello word";

	public static void main(String[] argv) throws  IOException, TimeoutException {

		//socket connection
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost"); // otherwise IP address
		Connection connection = factory.newConnection();

		//channel with API
		Channel channel = connection.createChannel();

		//declare queue
		//1. queue name
		//2.
		channel.queueDeclare("myQueue", false, false, false, null);

		//message content in byte array
		channel.basicPublish("", "myQueue", null, msg.getBytes());

		//print info
		System.out.println(" [x] Sent '" + msg + "'");

		//close channel
		channel.close();
		//close connection
		connection.close();
	}
}
