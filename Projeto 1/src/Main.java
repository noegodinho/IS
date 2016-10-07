import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;

public class Main{
	public static void main(String[] args){
		String websiteToParse = "https://www.rio2016.com/en/medal-count-country";
		Document doc;
		Elements links;

		try{
			doc = Jsoup.connect(websiteToParse).get();
			System.out.println(doc.title() + "\n");
			links = doc.getElementsByTag("tr");
			String linkText;

			for(int i = 3; i < links.size() - 9; ++i){
				linkText = links.get(i).text();

				if(!Character.isLetter(linkText.charAt(0))){
					linkText = "\n" + linkText;
					++i;
				}

				if(linkText.substring(0, 6).equals("Bronze") || linkText.substring(0, 6).equals("Silver")){
					String temp = linkText.substring(0, 6) + "\n";
					String temp2 = linkText.substring(7, linkText.length());
					linkText = temp + temp2;
				}

				else if(linkText.substring(0, 4).equals("Gold")){
					String temp = linkText.substring(0, 4) + "\n";
					String temp2 = linkText.substring(5, linkText.length());
					linkText = temp + temp2;
				}

				System.out.println(linkText);
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public void createMarshal(Olympics olympics){
		try{
			File file = new File("xml/medals.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Olympics.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(olympics, file);
		}catch(JAXBException jaxbe){
			jaxbe.printStackTrace();
		}
	}
}
