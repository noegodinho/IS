import org.xml.sax.SAXException;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MedalsKeeper implements MessageListener{
    public String xml;
    private boolean validating;
    private final Object available;
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
        this.validating = false;
        new ReceiveMessages().start();

        this.available = new Object();
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

            while(this.validating){
                try{
                    synchronized(this.available){
                        this.available.wait();
                    }
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }

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
        String[] splitted = query.split(",");
        String toReturn;
        query = splitted[0];

        ArrayList<Olympics.Country.Athlete> results = new ArrayList<>();

        for(Olympics.Country country : this.olympicMedals.getCountry()){
            if(country.getName().toUpperCase().equals(query) || country.getAbbreviation().toUpperCase().equals(query)){
                results = (ArrayList<Olympics.Country.Athlete>) country.getAthlete().clone();
                break;
            }

            else{
                for(Olympics.Country.Athlete athlete : country.getAthlete()){
                    if(athlete.getMedal().toUpperCase().equals(query) || athlete.getName().toUpperCase().equals(query)
                            || athlete.getModality().toUpperCase().equals(query) || athlete.getSpeciality().toUpperCase().equals(query)){
                        results.add(athlete);
                    }
                }
            }
        }

        ArrayList<Olympics.Country.Athlete> temp = new ArrayList<>();

        for(int i = 1; i < splitted.length; ++i){
            for(Olympics.Country.Athlete athlete : results){
                if(athlete.getMedal().toUpperCase().equals(splitted[i]) || athlete.getName().toUpperCase().equals(splitted[i])
                        || athlete.getModality().toUpperCase().equals(splitted[i])
                        || athlete.getSpeciality().toUpperCase().equals(splitted[i])){
                }

                else{
                    temp.add(athlete);
                }
            }
        }

        for(Olympics.Country.Athlete t : temp){
            results.remove(t);
        }

        toReturn = results.toString();

        return (toReturn.equals("[]")) ? "Cannot find information" : toReturn;
    }

    private void unmarshallMessage(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Olympics.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(this.xml);
            this.olympicMedals = (Olympics) unmarshaller.unmarshal(reader);
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
                validating = true;
                xml = tmsg.getText();

                Source xmlDocToValidate = new StreamSource(new StringReader(xml));
                Source xsdFile = new StreamSource("xml/medals.xsd");

                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(xsdFile);

                Validator validator = schema.newValidator();
                validator.validate(xmlDocToValidate);

                System.out.println("XML Received from topic");
                validating = false;

                synchronized(available){
                    available.notify();
                }
            }catch(JMSException jmse){
                jmse.printStackTrace();
            }catch(SAXException e){
                //e.printStackTrace();
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
