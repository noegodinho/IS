import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class MedalsKeeper{
    public static void main(String[] args){
        try{
            ReceiveMessages rm = new ReceiveMessages();
            rm.launchAndWait();
            ReceiveMessagesQueue rmq = new ReceiveMessagesQueue();
            rmq.launchAndWait();
        }catch(NamingException ne){
            ne.printStackTrace();
        }
    }

    public static class ReceiveMessagesQueue implements MessageListener{
        private ConnectionFactory cf;
        private Destination d;

        public ReceiveMessagesQueue() throws NamingException{
            this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
            this.d = InitialContext.doLookup("jms/queue/KeeperReceiver");
        }

        @Override
        public void onMessage(Message msg){
            try{
                TextMessage tmsg = (TextMessage)msg;
                String message = tmsg.getText();
            }catch(JMSException jmse){
                jmse.printStackTrace();
            }
        }

        public void launchAndWait(){
            try(JMSContext jcontext = cf.createContext("Is", "isisisis")){
                JMSConsumer mc = jcontext.createConsumer(d);
                mc.setMessageListener(this);

                System.in.read();
            }catch(JMSRuntimeException | IOException re){
                re.printStackTrace();
            }
        }
    }

    public static class ReceiveMessages implements MessageListener{
        private ConnectionFactory cf;
        private Destination d;

        public ReceiveMessages() throws NamingException{
            this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
            this.d = InitialContext.doLookup("jms/topic/XMLTopic");
        }

        @Override
        public void onMessage(Message msg){
            try{
                TextMessage tmsg = (TextMessage)msg;
                String xml = tmsg.getText();
                System.out.println(xml);
            }catch(JMSException jmse){
                jmse.printStackTrace();
            }
        }

        public void launchAndWait(){
            try(JMSContext jcontext = cf.createContext("Is", "isisisis")){
                jcontext.setClientID("keeper");
                JMSConsumer mc = jcontext.createDurableConsumer((Topic) d, "keeper");
                mc.setMessageListener(this);

                System.in.read();
            }catch(JMSRuntimeException | IOException re){
                re.printStackTrace();
            }
        }
    }
}
