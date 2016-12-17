package ejbservices;

import ejbcrud.QueriesCrudLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import java.util.Date;
import java.util.List;

@Stateless
public class ScriptBean implements ScriptBeanRemote{
    @EJB
    private QueriesCrudLocal queriesCrud;
    private Logger logger;

    public ScriptBean(){
        this.logger = LoggerFactory.getLogger(ScriptBean.class);
    }

    public boolean createAdminAccount(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                                      String address, Integer telephone){

        return this.queriesCrud.insertAdmin(hashedPassword, name, birth, instEmail, altEmail, address, telephone);
    }

    public boolean adminExists(String instEmail){
        if(this.queriesCrud.selectAdmins(instEmail).size() == 0){
            if(this.queriesCrud.selectProfessors(instEmail).size() == 0){
                if(this.queriesCrud.selectStudents(instEmail).size() == 0){
                    logger.info("Admin does not exist");
                    return false;
                }
            }
        }

        logger.info("Admin exists");

        return true;
    }

    public List<String> getAdmins(){
        return this.queriesCrud.selectAdminsEmail();
    }

    public boolean deleteAdmin(String instEmail){
        return this.queriesCrud.deleteAdmin(instEmail);
    }
}
