package ejb;

import data.Course;
import data.Professor;
import data.Student;
import data.User;

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
public class AdminBean implements AdminBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public AdminBean(){
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }

    public boolean createStudentAccount(String hashedPassword, String name, Date birth, String instEmail,
                                        String altEmail, String address, Integer telephone, Integer number,
                                        Integer yearOfCourse){
        try{
            User student = new Student(hashedPassword, name, birth, instEmail, altEmail, address, telephone, 2, number,
                                       yearOfCourse);
            entityManager.persist(student);
            logger.info("Student: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean createProfessorAccount(String hashedPassword, String name, Date birth, String instEmail,
                                          String altEmail, String address, Integer telephone, Integer internalNumber,
                                          String category, String office, Integer internalTelephoneNumber,
                                          double salary){
        try{
            User professor = new Professor(hashedPassword, name, birth, instEmail, altEmail, address, telephone, 1,
                                           internalNumber, category, office, internalTelephoneNumber, salary);
            entityManager.persist(professor);
            logger.info("Professor: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editStudent(String hashedPassword, String name, Date birth, String instEmail,
                               String altEmail, String address, Integer telephone, Integer number,
                               Integer yearOfCourse, List<Course> courses, String newInstEmail){
        try{
            Query query = entityManager.createQuery("Select s from Student s where s.instEmail = " + instEmail);
            Student student = (Student)query.getSingleResult();

            student.setHashedPassword(hashedPassword);
            student.setName(name);
            student.setBirth(birth);
            student.setInstEmail(newInstEmail);
            student.setAltEmail(altEmail);
            student.setAddress(address);
            student.setTelephone(telephone);
            student.setNumber(number);
            student.setYearOfCourse(yearOfCourse);
            student.setCourses(courses);

            entityManager.persist(student);
            logger.info("Student: " + name + " successfully edited");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                                 String altEmail, String address, Integer telephone, Integer internalNumber,
                                 String category, String office, Integer internalTelephoneNumber, double salary,
                                 List<Course> courses, String newInstEmail){
        try{
            Query query = entityManager.createQuery("Select p from Professor p where p.instEmail = " + instEmail);
            Professor professor = (Professor)query.getSingleResult();

            professor.setHashedPassword(hashedPassword);
            professor.setName(name);
            professor.setBirth(birth);
            professor.setInstEmail(newInstEmail);
            professor.setAltEmail(altEmail);
            professor.setAddress(address);
            professor.setTelephone(telephone);
            professor.setInternalNumber(internalNumber);
            professor.setCategory(category);
            professor.setOffice(office);
            professor.setInternalTelephoneNumber(internalTelephoneNumber);
            professor.setSalary(salary);
            professor.setCourses(courses);

            entityManager.persist(professor);
            logger.info("Professor: " + name + " successfully edited");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteUser(String instEmail){
        try{
            Query query = entityManager.createQuery("(Select s from Student s where s.instEmail = " + instEmail + ") " +
                                                    "UNION " +
                                                    "(Select p from Professor p where p.instEmail = " + instEmail + ")");
            User user = (User)query.getSingleResult();
            entityManager.remove(user); /* not sure if this works */
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }
}