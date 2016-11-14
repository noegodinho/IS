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
import java.util.List;

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
            Query query = entityManager.createQuery("Select a.id from Administrator a where a.instEmail like ?1");
            query.setParameter(1, instEmail);

            if(query.getResultList().size() != 0){
                query = entityManager.createQuery("Select s.id from Student s where s.instEmail like ?1");
                query.setParameter(1, instEmail);

                if(query.getResultList().size() != 0){
                    query = entityManager.createQuery("Select p.id from Professor p where p.instEmail like ?1");
                    query.setParameter(1, instEmail);

                    if(query.getResultList().size() != 0){
                        logger.info("Admin exists");
                        return true;
                    }
                }
            }

            logger.info("Admin does not exist");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return true;
        }

        return false;
    }

    public List<String> getAdmins(){
        List<String> admins = null;

        try{
            Query query = entityManager.createQuery("Select a.instEmail from Administrator a");
            admins = query.getResultList();

            logger.info("Admin list successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return admins;
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
