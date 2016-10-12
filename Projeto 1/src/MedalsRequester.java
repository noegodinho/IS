import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
        this.d = InitialContext.doLookup("jms/queue/KeeperReceiver");
        send("USA");
    }

    private void send(String text){
        try(JMSContext jcontext = cf.createContext("Is", "isisisis")){
            JMSProducer mp = jcontext.createProducer();
            mp.send(d, text);
        }catch (JMSRuntimeException re){
            re.printStackTrace();
        }
    }
}
