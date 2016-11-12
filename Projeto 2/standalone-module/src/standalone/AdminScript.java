package standalone;

import ejb.ScriptBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Scanner;

public class AdminScript{
    public static void main(String[] args) throws NamingException{
        ScriptBeanRemote scriptBeanRemote = (ScriptBeanRemote)InitialContext.doLookup("ejb-module/ScriptBean!ejb.ScriptBeanRemote");

        menu(scriptBeanRemote);
        System.out.println(scriptBeanRemote.adminExists("test"));
    }

    private static void menu(ScriptBeanRemote scriptBeanRemote){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        System.out.println("Admin script: ");

        while(option != 3){
            System.out.println("1 - Add an admin account");
            System.out.println("2 - Delete an admin account");
            System.out.println("3 - Exit");
            System.out.println("Choose your option: ");
            option = sc.nextInt();

            switch(option){
                case 1:
                    addAdmin(scriptBeanRemote, sc);
                    break;
                case 2:
                    deleteAdmin(scriptBeanRemote, sc);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid option!\n");
            }
        }
    }

    private static void addAdmin(ScriptBeanRemote scriptBeanRemote, Scanner sc){

    }

    private static void deleteAdmin(ScriptBeanRemote scriptBeanRemote, Scanner sc){
        int option = 0;
        List<String> admins = scriptBeanRemote.getAdmins();

        if(admins == null){
            System.out.println("No admins available to delete!\n");
            return;
        }

        for(int i = 0; i < admins.size(); ++i){
            System.out.println((i + 1) + " - Username: " + admins.get(i));
        }

        while(option < 1 || option > admins.size()){
            System.out.println("Choose an admin to delete: ");
            option = sc.nextInt();

            if(option < 1 || option > admins.size()){
                System.out.println("Invalid option!\n");
            }
        }
    }
}
