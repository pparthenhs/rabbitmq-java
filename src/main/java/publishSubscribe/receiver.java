package publishSubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by parthenis on 21/08/2017.
 */
public class receiver {

	public static void main(String args[]) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();


		//declare exchange
		channel.exchangeDeclare("myEx",BuiltinExchangeType.FANOUT);

		String queueName = channel.queueDeclare().getQueue();

		//binf queue with the exchange
		channel.queueBind(queueName,"myEx","");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
									   AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-32");
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}
