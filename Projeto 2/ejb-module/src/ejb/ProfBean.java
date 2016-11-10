package ejb;

import data.Course;
import data.Material;
import data.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ProfBean implements ProfBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public ProfBean(){
        this.logger = LoggerFactory.getLogger(ProfBean.class);
    }

    public boolean updateMaterial(String filename, Course course){
        try{
            Material material = new Material(filename, course);
            entityManager.persist(material);

            logger.info("Material: " + filename + " successfully uploaded");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteMaterial(String filename){
        try{
            Query query = entityManager.createQuery("Delete from Material m where m.filename = " + filename);
            query.executeUpdate();

            logger.info("Material: " + filename + " successfully deleted");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public List<Student> getStudentsByCourse(String courseName, boolean ascendingOrder){
        List<Student> students = null;

        try{
            Query query = entityManager.createQuery("Select c.students from Course c where c.courseName = " + courseName);

            students = query.getResultList();

            if(ascendingOrder){
                students.sort((Student s1, Student s2) -> s1.getName().compareTo(s2.getName()));
            }

            else{
                students.sort((Student s1, Student s2) -> s2.getName().compareTo(s1.getName()));
            }

            logger.info("Students from Course: " + courseName + " successfully returned");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return students;
    }
}
