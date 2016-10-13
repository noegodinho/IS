import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WebCrawler{
	public static void main(String[] args){
		String websiteToParse = "https://www.rio2016.com/en/medal-count-country";
		Document doc;
		Elements links;

		Olympics olympics = new Olympics();
		ArrayList<Olympics.Country> countries = olympics.getCountry();
		ArrayList<Olympics.Country.Athlete> athletes = new ArrayList<>();

		try{
			doc = Jsoup.connect(websiteToParse).get();
			System.out.println(doc.title() + "\n");
			links = doc.getElementsByTag("tr");

			Olympics.Country country = new Olympics.Country();
			Olympics.Country.Athlete athlete;

			int country_pos = 0;
			String actual_medal = "";
			String actual_country_name;
			Elements el;

			for(int i = 3; i < links.size() - 9; ++i){
				el = links.get(i).getElementsByTag("td");

				if(el.size() > 4){
					if(country_pos != 0){
						country.setAthlete(athletes);
						countries.add(country);
					}

					athletes = new ArrayList<>();
					country = new Olympics.Country();

					++i;

					country_pos = Integer.parseInt(el.get(0).getElementsByAttributeValueContaining("class", "\"col-1\"").text());
					country.setPosition(country_pos);

					actual_country_name = el.get(2).getElementsByAttributeValueContaining("class", "\"col-3\"").text();

					country.setName(actual_country_name);
					country.setAbbreviation(el.get(1).getElementsByAttributeValueContaining("class", "\"col-2\"").text());
					country.setGold(Integer.parseInt(el.get(3).getElementsByAttributeValueContaining("class", "\"col-4\"").text()));
					country.setSilver(Integer.parseInt(el.get(4).getElementsByAttributeValueContaining("class", "\"col-5\"").text()));
					country.setBronze(Integer.parseInt(el.get(5).getElementsByAttributeValueContaining("class", "\"col-6\"").text()));
					country.setTotal();
				}

				else{
					athlete = new Olympics.Country.Athlete();

					String temp = el.get(0).getElementsByAttributeValueContaining("class", "\"col-1\"").text();

					if(!temp.isEmpty()){
						actual_medal = temp;
					}

					athlete.setModality(el.get(1).getElementsByAttributeValueContaining("class", "\"col-2\"").text() +
							el.get(2).getElementsByAttributeValueContaining("class", "\"col-3\"").text());
					athlete.setName(el.get(3).getElementsByAttributeValueContaining("class", "\"col-4\"").text());
					athlete.setMedal(actual_medal);

					athletes.add(athlete);
				}
			}

			country.setAthlete(athletes);
			countries.add(country);

			createMarshal(olympics);
			new Sender();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	public static void createMarshal(Olympics olympics){
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

	public static class Sender{
		private ConnectionFactory cf;
		private Destination d;

		public Sender() throws FileNotFoundException{
			int seconds = 10;
			boolean trying = true;

			while(trying){
				try{
					this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
					this.d = InitialContext.doLookup("jms/topic/XMLTopic");
					trying = false;
				}catch(Exception e){
					System.out.println("JMS Topic is down... Trying again in " + seconds + "s.");

					try{
						Thread.sleep(1000 * seconds);
					}catch (Exception ex){
						ex.printStackTrace();
					}

					seconds *= 2;
				}
			}

			send(new Scanner(new File("xml/medals.xml")).useDelimiter("\\Z").next());
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
