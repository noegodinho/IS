import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class HTMLSummary{
    public static void main(String[] args){
        try{
            while(true){
                Receiver r = new Receiver();
                String xml = r.receive();
                System.out.println("Message: " + xml);

                TransformerFactory tFactory = TransformerFactory.newInstance();

                Source xslDoc = new StreamSource("xml/medals.xsl");
                StringReader xmlD = new StringReader(xml);
                Source xmlDoc = new StreamSource(xmlD);

                String outputFileName = "xml/medals.html";
                OutputStream htmlFile = new FileOutputStream(outputFileName);

                Transformer transformer = tFactory.newTransformer(xslDoc);
                transformer.transform(xmlDoc, new StreamResult(htmlFile));
                System.out.println("HTML file created successfully");
            }
        }catch(Exception e){
            e.printStackTrace();
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

            try(JMSContext jcontex = cf.createContext("Is", "isisisis")) {
                JMSConsumer mc = jcontex.createConsumer(d);
                msg = mc.receiveBody(String.class);
            } catch (JMSRuntimeException re) {
                re.printStackTrace();
            }

            return msg;
        }
    }
}
