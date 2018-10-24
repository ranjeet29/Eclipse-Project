import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;




public class SendandReceive {
	
	static String serverUrl = "tcp://10.10.30.13:7234";
	static String userName = "admin";
	static String password = "null"; 
	    
	public static void main(String[] args) {
		sendTopicMessage("queue1","This is the message content for demo !");
	}
	
	
	
	public static void sendTopicMessage(String topicName, String messageStr) {

	    Connection connection = null;
	    Session session = null;
	    MessageProducer msgProducer = null;
	    Destination destination = null;

	    try {
	        TextMessage msg;

	        System.out.println("Publishing to destination '" + topicName+ "'\n");

	        ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(serverUrl);

	        connection = factory.createConnection(userName, password);

	        /* create the session */
	        session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
	        
	        System.out.println("sessions : "+session);

	        /* create the destination */
	        destination = session.createQueue(topicName);
	        
	        System.out.println("destination : "+destination);

	        /* create the producer */
	        msgProducer = session.createProducer(destination);

	        /* publish messages */
	        /* create text message */
	        msg = session.createTextMessage();
	        
	        System.out.println("msg : "+msg);

	        /* set message text */
	        msg.setText(messageStr);

	        
	        /* publish message */
	        msgProducer.send(destination, msg);

	        System.out.println("Published message: " + messageStr);

	        /* close the connection */
	        connection.close();

	    } catch (JMSException e) {
	        e.printStackTrace();
	    }
	}
}


