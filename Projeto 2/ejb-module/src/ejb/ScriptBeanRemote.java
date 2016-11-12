package ejb;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ScriptBeanRemote{
    boolean createAdminAccount(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                               String address, int telephone);
    boolean adminExists(String instEmail);
    List<String> getAdmins();
    boolean deleteAdmin(String instEmail);
}
