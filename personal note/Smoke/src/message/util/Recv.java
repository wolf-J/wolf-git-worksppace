package message.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


public class Recv {
	private static final String QUEUE_NAME="hello";
	
	private static final String EXCHANGE_NAME = "direct_logs";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "warning");
		
		System.out.println("[*] Waiting for messages... To exit press CTRL + C");
		
		Consumer consumer = new DefaultConsumer(channel){
			 public void handleDelivery(String consumerTag,
                     Envelope envelope,
                     AMQP.BasicProperties properties,
                     byte[] body) throws IOException
			 		{
				 		String message = new String(body,"UTF-8");
				 		System.out.println("[x] Received: '"+message+"'");
			 		}
		};
		channel.basicConsume(queueName, true, consumer);
		// TODO Auto-generated method stub
		/*ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection=factory.newConnection();
		Channel channel=connection.createChannel();
		
		
		
		boolean durable=true;
		channel.queueDeclare(QUEUE_NAME, durable, false,false , null);
		channel.basicQos(1);
		
		System.out.println("[*] Waiting for messages. To exit press CTRL+C");
		
		Consumer consumer= new DefaultConsumer(channel){
			
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
			{
				String message=new String(body,"UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				
				try
				{
					doWork(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
				{
					System.out.println("[x] done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		
		boolean autoAck=false;
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);
		*/
	}
	
	private static void doWork(String task) throws InterruptedException 
	{
		for(char ch : task.toCharArray())
		{
			if(ch =='.' )
				Thread.sleep(1000);
		}
	}
}
