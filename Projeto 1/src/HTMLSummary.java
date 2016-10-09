import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Rocha on 08/10/16.
 */
public class HTMLSummary {
    public static void main(String[] args){
        try
        {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xslDoc = new StreamSource("xml/medals.xsl");
            Source xmlDoc = new StreamSource("xml/medals.xml");

            String outputFileName = "xml/medals.html";
            OutputStream htmlFile = new FileOutputStream(outputFileName);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
            System.out.println("HTML file created successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
