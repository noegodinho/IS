import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class MedalsKeeper implements MessageListener{
    public String xml;
    private ConnectionFactory cf;
    private Destination d;
    private JMSContext jcontext;

    public static void main(String[] args){
        try{
            new MedalsKeeper();
        }catch(NamingException ne){
            ne.printStackTrace();
        }
    }

    public MedalsKeeper() throws NamingException{
        new ReceiveMessages().start();

        this.xml = null;
        this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
        this.d = InitialContext.doLookup("jms/queue/KeeperRequester");
        this.launchAndWait();
    }

    @Override
    public void onMessage(Message msg){
        try{
            TextMessage tmsg = (TextMessage)msg;
            String message = tmsg.getText();
            String send;

            if(this.xml == null){
                send = "Cannot find information";
            }

            else{
                send = findQuery(message);
            }

            TextMessage reply = jcontext.createTextMessage(send);
            JMSProducer jms = jcontext.createProducer();
            jms.send(tmsg.getJMSReplyTo(), reply);

            System.out.println("Message sent!");
        }catch(JMSException jmse){
            jmse.printStackTrace();
        }
    }

    private String findQuery(String query){
        return "some text";
    }

    private void launchAndWait(){
        try{
            jcontext = cf.createContext("Is", "isisisis");
            JMSConsumer mc = jcontext.createConsumer(d);
            mc.setMessageListener(this);

            System.in.read();
        }catch(JMSRuntimeException | IOException re){
            re.printStackTrace();
        }
    }

    public class ReceiveMessages extends Thread implements MessageListener{
        private ConnectionFactory cf;
        private Destination d;

        public ReceiveMessages() throws NamingException{
            this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
            this.d = InitialContext.doLookup("jms/topic/XMLTopic");
        }

        @Override
        public void run(){
            this.launchAndWait();
        }

        @Override
        public void onMessage(Message msg){
            try{
                TextMessage tmsg = (TextMessage)msg;
                xml = tmsg.getText();
                System.out.println("XML Received from topic");
            }catch(JMSException jmse){
                jmse.printStackTrace();
            }
        }

        private void launchAndWait(){
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
