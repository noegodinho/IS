package standalone;

import artifact.Soap;
import artifact.SoapService;

import java.util.Scanner;

/*create table Subscription (id INT(3) AUTO_INCREMENT PRIMARY KEY, email varchar(50) NOT NULL, course varchar(50) NOT NULL);
create table Statistics(numberEmails INT(5) NOT NULL);*/
public class StandaloneCommandLine{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        String input;

        SoapService soapService = new SoapService();
        Soap soap = soapService.getSoapPort();

        while(option != 4){
            System.out.println("1 - Subscribe to a Course List");
            System.out.println("2 - Remove a Course List Subscription");
            System.out.println("3 - List all Subscriptions");
            System.out.println("4 - Exit");
            System.out.println("Choose your option: ");
            input = sc.nextLine();

            try{
                option = Integer.parseInt(input);
            }catch(Exception e){
                continue;
            }

            switch(option){
                case 1:
                    addSubscription(soap, sc);
                    break;
                case 2:
                    removeSubscription(soap, sc);
                    break;
                case 3:
                    listSubscriptions(soap);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid option!\n");
            }
        }
    }

    public static void addSubscription(Soap soap, Scanner sc){
        String email = "", course = "";

        while(email.isEmpty()){
            System.out.println("Insert an email to subscribe: ");
            email = sc.nextLine();
        }

        while(course.isEmpty()){
            System.out.println("Insert an existent course to subscribe: ");
            course = sc.nextLine();
        }

        System.out.println(soap.addSubscription(email, course));
    }

    public static void removeSubscription(Soap soap, Scanner sc){
        String email = "", course = "";

        while(email.isEmpty()){
            System.out.println("Insert an existent email: ");
            email = sc.nextLine();
        }

        while(course.isEmpty()){
            System.out.println("Insert an existent course: ");
            course = sc.nextLine();
        }

        System.out.println(soap.removeSubscription(email, course));
    }

    public static void listSubscriptions(Soap soap){
        System.out.println(soap.listSubscriptions()); // ?
    }
}
