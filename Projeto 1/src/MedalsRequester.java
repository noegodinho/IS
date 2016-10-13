import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class MedalsRequester{
    private ConnectionFactory cf;
    private Destination d;

    public static void main(String[] args){
        try{
            new MedalsRequester();
        }catch(NamingException ne){
            ne.printStackTrace();
        }
    }

    public MedalsRequester() throws NamingException{
        this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
        this.d = InitialContext.doLookup("jms/queue/KeeperRequester");
        Scanner scan = new Scanner(System.in);
        System.out.println("Query like ?,?,? (no spaces): ");
        String arg = scan.nextLine();
        send(arg);
    }

    private void send(String text){
        try(JMSContext jcontext = cf.createContext("Is", "isisisis")){
            JMSProducer mp = jcontext.createProducer();
            TextMessage txt = jcontext.createTextMessage();
            Destination reply = jcontext.createTemporaryQueue();
            txt.setJMSReplyTo(reply);
            txt.setText(text);

            mp.send(d, txt);

            JMSConsumer jms = jcontext.createConsumer(reply);
            TextMessage tm = (TextMessage)jms.receive();
            System.out.println("Reply: \n" + tm.getText());
        }catch (JMSRuntimeException | JMSException re){
            re.printStackTrace();
        }
    }
}
