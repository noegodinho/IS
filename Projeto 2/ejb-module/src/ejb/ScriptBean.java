package ejb;

import data.Administrator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Date;

@Stateless
public class ScriptBean implements ScriptBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public ScriptBean(){
        this.logger = LoggerFactory.getLogger(ScriptBean.class);
    }

    public boolean createAdminAccount(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                                      String address, int telephone){
        try{
            Administrator administrator = new Administrator(hashedPassword, name, birth, instEmail, altEmail, address,
                                                            telephone);
            entityManager.persist(administrator);

            logger.info("Admin: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean adminExists(String instEmail){
        try{
            Query query = entityManager.createQuery("Select a from Administrator a where a.instEmail like ?1");
            query.setParameter(1, instEmail);

            if(query.getResultList() != null){
                logger.info("Admin exists");
                return true;
            }

            logger.info("Admin does not exist");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return true;
        }

        return false;
    }

    public boolean deleteAdmin(String instEmail){
        try{
            Query query = entityManager.createQuery("Delete from Administrator a where a.instEmail like ?1");
            query.setParameter(1, instEmail);
            query.executeUpdate();

            logger.info("Admin: " + instEmail + " successfully deleted");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }
}