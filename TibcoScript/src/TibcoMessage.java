import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;




public class TibcoMessage {
	
	private Connection connection; 
	private Session session;
	private MessageProducer producer;
	private Queue queue;

	public void init(Context ctx, String queueName) {

	    try {
	        ConnectionFactory cnf = (ConnectionFactory) ctx.lookup(queueName);
	        queue = (Queue) ctx.lookup(queueName);


	        connection = cnf.createConnection("user", "user123");
	        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        producer = session.createProducer(queue);

	        connection.start();
	    } catch (NamingException e) {
	        throw new RuntimeException(e);
	    } catch (JMSException e) {
	        throw new RuntimeException(e);
	    }
	}	

}

