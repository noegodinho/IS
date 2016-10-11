import org.xml.sax.SAXException;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class HTMLSummary{
    public static void main(String[] args){
        while(true){
            try{
                Receiver r = new Receiver();
                String xml = r.receive();
                //System.out.println("Message: " + xml);

                TransformerFactory tFactory = TransformerFactory.newInstance();

                Source xmlDocToValidate = new StreamSource(new StringReader(xml));
                Source xmlDoc = new StreamSource(new StringReader(xml));

                Source xsdFile = new StreamSource("xml/medals.xsd");

                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(xsdFile);
                Validator validator = schema.newValidator();

                String outputFileName = "xml/medals" + System.currentTimeMillis() + ".html";
                OutputStream htmlFile = new FileOutputStream(outputFileName);

                Source xslDoc = new StreamSource("xml/medals.xsl");

                try{
                    validator.validate(xmlDocToValidate);

                    Transformer transformer = tFactory.newTransformer(xslDoc);
                    transformer.transform(xmlDoc, new StreamResult(htmlFile));

                    System.out.println("HTML file created successfully");
                }catch(SAXException saxe){
                    saxe.printStackTrace();
                    System.out.println("Message is NOT a valid XML message");
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class Receiver{
        private ConnectionFactory cf;
        private Destination d;

        public Receiver() throws NamingException{
            this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
            this.d = InitialContext.doLookup("jms/topic/XMLTopic");
        }

        private String receive(){
            String msg = null;

            try(JMSContext jcontext = cf.createContext("Is", "isisisis")){
                jcontext.setClientID("summary");
                JMSConsumer mc = jcontext.createDurableConsumer((Topic) d, "summary");
                msg = mc.receiveBody(String.class);
            }catch(JMSRuntimeException re){
                re.printStackTrace();
            }

            return msg;
        }
    }
}
