package routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by parthenis on 21/08/2017.
 */
public class sender {


	static String msg1 ="error word";
	static String msg2 ="info word";
	static String msg3 ="warnign word";

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
		for(int i=0; i<10; i++){
			channel.basicPublish("myNew","error", null, msg1.getBytes());
			channel.basicPublish("myNew","info", null, msg2.getBytes());
			channel.basicPublish("myNew","warning", null, msg3.getBytes());

			System.out.println("msg " + i +" sended");
		}

		//close channel
		channel.close();
		//close connection
		connection.close();
	}
}
