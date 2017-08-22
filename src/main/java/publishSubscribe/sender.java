package publishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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

		channel.basicPublish("myEx", "", null, msg.getBytes("UTF-32"));

		//print info
		System.out.println(" [x] Sent '" + msg + "'");

		//close channel
		channel.close();
		//close connection
		connection.close();
	}
}
