import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
			String linkText;
			String[] splitted;
			Olympics.Country country = new Olympics.Country();
			Olympics.Country.Athlete athlete;
			int country_pos = 0;
			String actual_medal = "";
			String[] actual_country_name = new String[2];

			for(int i = 3; i < links.size() - 9; ++i){
				linkText = links.get(i).text();

				if(!Character.isLetter(linkText.charAt(0))){
					if(country_pos != 0){
						country.setAthlete(athletes);
						countries.add(country);
					}

					athletes = new ArrayList<>();
					country = new Olympics.Country();
					splitted = linkText.split("\\s+");

					linkText = "\n" + linkText;
					++i;

					country_pos = Integer.parseInt(splitted[0]);
					country.setPosition(country_pos);

					String temp = "";
					actual_country_name = new String[splitted.length - 6];

					for(int k = 2, j = 0; k < splitted.length - 4; ++k, ++j){
						temp += splitted[k] + " ";
						actual_country_name[j] = splitted[k];
					}

					temp = temp.substring(0, temp.length() - 1);

					country.setName(temp);
					country.setAbbreviation(splitted[1]);
					country.setGold(Integer.parseInt(splitted[splitted.length - 4]));
					country.setSilver(Integer.parseInt(splitted[splitted.length - 3]));
					country.setBronze(Integer.parseInt(splitted[splitted.length - 2]));
					country.setTotal();
				}

				else if(linkText.substring(0, 6).equals("Bronze") || linkText.substring(0, 6).equals("Silver")){
					actual_medal = linkText.substring(0, 6);

					String temp = linkText.substring(0, 6) + "\n";
					String temp2 = linkText.substring(7, linkText.length());
					linkText = temp + temp2;

					athlete = new Olympics.Country.Athlete();
					temp = temp2.replaceAll("/", "");
					splitted = temp.split("\\s+");

					athlete.setName(createString(actual_country_name, splitted));
					athlete.setMedal(actual_medal);
					athlete.setModality(createString2(actual_country_name, splitted));

					athletes.add(athlete);
				}

				else if(linkText.substring(0, 4).equals("Gold")){
					actual_medal = "Gold";

					String temp2 = linkText.substring(5, linkText.length());
					linkText = "Gold\n" + temp2;

					athlete = new Olympics.Country.Athlete();
					temp2 = temp2.replaceAll("/", "");
					splitted = temp2.split("\\s+");

					athlete.setName(createString(actual_country_name, splitted));
					athlete.setMedal(actual_medal);
					athlete.setModality(createString2(actual_country_name, splitted));

					athletes.add(athlete);
				}

				else{
					athlete = new Olympics.Country.Athlete();
					String temp = linkText.replaceAll("/", "");
					splitted = temp.split("\\s+");

					athlete.setName(createString(actual_country_name, splitted));
					athlete.setMedal(actual_medal);
					athlete.setModality(createString2(actual_country_name, splitted));

					athletes.add(athlete);
				}

				//System.out.println(linkText);
			}

			country.setAthlete(athletes);
			countries.add(country);

			createMarshal(olympics);
			new Sender();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String createString(String[] actual_country_name, String[] splitted){
		String temp = "";

		if(actual_country_name[actual_country_name.length - 1].equals(splitted[splitted.length - 1])){
			for(int k = splitted.length - actual_country_name.length; k < splitted.length; ++k){
				temp +=	splitted[k] + " ";
			}
		}

		else{
			for(int k = splitted.length - 2; k < splitted.length; ++k){
				temp +=	splitted[k] + " ";
			}
		}

		return temp.substring(0, temp.length() - 1);
	}

	public static String createString2(String[] actual_country_name, String[] splitted){
		String temp = "";

		if(actual_country_name[actual_country_name.length - 1].equals(splitted[splitted.length - 1])){
			for(int k = 0; k < splitted.length - actual_country_name.length; ++k){
				temp +=	splitted[k] + " ";
			}
		}

		else{
			for(int k = 0; k < splitted.length - 2; ++k) {
				temp += splitted[k] + " ";
			}
		}

		return temp.substring(0, temp.length() - 1);
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

		public Sender() throws NamingException, FileNotFoundException{
			int seconds = 10;
			boolean trying = true;

			while(trying){
				try{
					this.cf = InitialContext.doLookup("jms/RemoteConnectionFactory");
					this.d = InitialContext.doLookup("jms/topic/XMLTopic");
					trying = false;
				}catch (Exception e){
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
