package ejb;

import javax.ejb.Remote;
import java.util.Date;

@Remote
public interface ScriptBeanRemote{
    boolean createAdminAccount(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                               String address, int telephone);
    boolean adminExists(String instEmail);
    boolean deleteAdmin(String instEmail);
}
