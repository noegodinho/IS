import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MedalsRequester{
    public static void main(String[] args){
        try{
            new Sender();
        }catch(NamingException ne){
            ne.printStackTrace();
        }
    }

    public static class Sender{
        private ConnectionFactory cf;
        private Destination d;

        public Sender() throws NamingException{
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
}
