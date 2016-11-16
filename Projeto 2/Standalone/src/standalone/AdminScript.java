package standalone;

import ejbservices.ScriptBeanRemote;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AdminScript{
    public static void main(String[] args) throws NamingException{
        ScriptBeanRemote scriptBeanRemote = (ScriptBeanRemote)InitialContext.doLookup("Web/ScriptBean!ejbservices.ScriptBeanRemote");

        menu(scriptBeanRemote);
    }

    private static void menu(ScriptBeanRemote scriptBeanRemote){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        String input;

        System.out.println("Admin script: ");

        while(option != 3){
            System.out.println("1 - Add an admin account");
            System.out.println("2 - Delete an admin account");
            System.out.println("3 - Exit");
            System.out.println("Choose your option: ");
            input = sc.nextLine();

            try{
                option = Integer.parseInt(input);
            }catch(Exception e){
                continue;
            }

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
        String instEmail = null;
        boolean adminExists = true;

        System.out.println("Fill the following fields to create an admin: ");

        while(adminExists){
            System.out.println("Institutional Email: ");
            instEmail = sc.nextLine();

            adminExists = scriptBeanRemote.adminExists(instEmail);

            if(adminExists){
                System.out.println("Admin already exists!\n");
            }
        }

        String hashedPassword = passwordHashing(fillInfo("Password: ", sc));
        String name = fillInfo("Name: ", sc);

        Date birth = null;
        while(birth == null){
            birth = joinDate(sc);
        }

        String altEmail = fillInfo("Alternative Email: ", sc);
        String address = fillInfo("Address: ", sc);

        Integer telephone = 0;
        while(telephone == 0){
            try{
                telephone = Integer.parseInt(fillInfo("Telephone: ", sc));
            }catch(Exception e){
                telephone = 0;
            }
        }

        scriptBeanRemote.createAdminAccount(hashedPassword, name, birth, instEmail, altEmail, address, telephone);
    }

    private static Date joinDate(Scanner sc){
        Integer day, month, year;

        while(true){
            try{
                day = Integer.parseInt(fillInfo("Day of birth: ", sc));
                break;
            }catch(Exception e){
                System.out.println("Invalid number!\n");
            }
        }

        while(true){
            try{
                month = Integer.parseInt(fillInfo("Month of birth: ", sc));
                break;
            }catch(Exception e){
                System.out.println("Invalid number!\n");
            }
        }

        while(true){
            try{
                year = Integer.parseInt(fillInfo("Year of birth: ", sc));
                break;
            }catch(Exception e){
                System.out.println("Invalid number!\n");
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        try{
            return cal.getTime();
        }catch(Exception e){
            System.out.println("Invalid date!\n");
        }

        return null;
    }

    private static String passwordHashing(String password){
        String hashedPassword = null;

        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < array.length; ++i){
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }

            hashedPassword = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return hashedPassword;
    }

    private static String fillInfo(String message, Scanner sc){
        String inputString;

        while(true){
            System.out.println(message);
            inputString = sc.nextLine();

            if(inputString.isEmpty()){
                System.out.println("Cannot be empty!\n");
            }

            else{
                break;
            }
        }

        return inputString;
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

        if(scriptBeanRemote.deleteAdmin(admins.get(option - 1))){
            System.out.println("Admin: " + admins.get(option - 1) + " successfully deleted!\n");
        }

        else{
            System.out.println("It was not possible to delete the admin!\n");
        }
    }
}
