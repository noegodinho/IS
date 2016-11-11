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
import java.util.Date;
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

    public List<Student> searchStudents(String name, Date birth, String instEmail, String altEmail, String address,
                                        Integer telephone, Integer number, Integer yearOfCourse){
        List<Student> students = null;

        try{
            String toQuery = "Select s from Student s where ";

            if(!name.isEmpty()){
                toQuery += "s.name = " + name + " and ";
            }

            if(!birth.toString().isEmpty()){
                toQuery += "s.birth = " + birth + " and ";
            }

            if(!instEmail.isEmpty()){
                toQuery += "s.instEmail = " + instEmail + " and ";
            }

            if(!altEmail.isEmpty()){
                toQuery += "s.altEmail = " + altEmail + " and ";
            }

            if(!address.isEmpty()){
                toQuery += "s.address = " + address + " and ";
            }

            if(!telephone.toString().isEmpty()){
                toQuery += "s.telephone = " + address + " and ";
            }

            if(!number.toString().isEmpty()){
                toQuery += "s.number = " + number + " and ";
            }

            if(!yearOfCourse.toString().isEmpty()){
                toQuery += "s.yearOfCourse = " + yearOfCourse;
            }

            if(toQuery.substring(toQuery.length() - 5, toQuery.length() - 1).equals("and ")){
                toQuery = toQuery.substring(0, toQuery.length() - 6);
            }

            Query query = entityManager.createQuery(toQuery);

            students = query.getResultList();

            logger.info("Student successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return students;
    }
}
