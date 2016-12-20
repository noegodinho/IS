package soap;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class Soap {

	@WebMethod
	public String addSubscription(String course, String email){
		return "";
	}
	
	@WebMethod
	public String removeSubscription(String course, String email){
		return "";
	}
	
	@WebMethod
	public String listSubscriptions(){
		return "";
	}
	
	
}
