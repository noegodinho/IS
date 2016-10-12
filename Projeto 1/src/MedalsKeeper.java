import org.xml.sax.SAXException;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MedalsKeeper implements MessageListener{
    public String xml;
    private ConnectionFactory cf;
    private Destination d;
    private JMSContext jcontext;
    private Olympics olympicMedals;

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
        this.olympicMedals = null;
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
                unmarshallMessage();
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
        query = query.toUpperCase();

        ArrayList<Olympics.Country.Athlete> results = new ArrayList<>();

        for(Olympics.Country country : this.olympicMedals.getCountry()){
            if(country.getName().toUpperCase().contains(query) || country.getAbbreviation().toUpperCase().contains(query)){
                return country.getAthlete().toString();
            }

            else{
                for(Olympics.Country.Athlete athlete : country.getAthlete()){
                    if(athlete.getMedal().toUpperCase().contains(query) || athlete.getName().toUpperCase().contains(query) || athlete.getModality().toUpperCase().contains(query)){
                        results.add(athlete);
                    }
                }

            }
        }

        String toReturn = results.toString();

        return (toReturn.equals("[]")) ? "Cannot find information" : toReturn;
    }

    private void unmarshallMessage(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Olympics.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(this.xml);
            this.olympicMedals= (Olympics) unmarshaller.unmarshal(reader);
        }catch(JAXBException e){
            e.printStackTrace();
        }
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
        public synchronized void run(){
            this.launchAndWait();
        }

        @Override
        public void onMessage(Message msg){
            try{
                TextMessage tmsg = (TextMessage)msg;
                xml = tmsg.getText();

                Source xmlDocToValidate = new StreamSource(new StringReader(xml));
                Source xsdFile = new StreamSource("xml/medals.xsd");

                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(xsdFile);

                Validator validator = schema.newValidator();
                validator.validate(xmlDocToValidate);

                System.out.println("XML Received from topic");
            }catch(JMSException jmse){
                jmse.printStackTrace();
            }catch(SAXException e){
                e.printStackTrace();
                System.out.println("Message is NOT a valid XML message.");
            }catch(IOException e){
                e.printStackTrace();
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
