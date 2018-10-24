import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


public class SendMessage {
    
    static String serverUrl = "tcp://10.10.30.13:7234"; // values changed
    static String userName = "admin";
    static String password = "null";
    static TextMessage message;

    public static void sendTopicMessage(String topicName, String messageStr) {

        Connection connection = null;
        Session session = null;
        MessageProducer msgProducer = null;
        Destination destination = null;


        try {
            TextMessage msg;

            System.out.println("Publishing to destination '" + topicName
                    + "'\n");

            ConnectionFactory factory = new com.tibco.tibjms.TibjmsConnectionFactory(serverUrl);

            connection = factory.createConnection(userName, password);


            session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);



            destination = session.createQueue(topicName);


            msgProducer = session.createProducer(null);



            msg = session.createTextMessage();

            msg.setStringProperty("SourceId", userName);
            msg.setStringProperty("BusinessEvent", password);


            msg.setText(messageStr);
            
            System.out.println("message :" +msg.getText());

            msgProducer.send(destination, msg);
            
            System.out.println("Published message: " + messageStr);

            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
   
    	sendTopicMessage("queue1" , "Hi Kaveri this is demo message ");
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}
    
