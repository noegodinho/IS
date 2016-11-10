package ejb;

import data.Course;
import data.Material;
import data.Student;
import data.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClientBean implements ClientBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public ClientBean(){
        this.logger = LoggerFactory.getLogger(ClientBean.class);
    }

    public User loginUser(String instEmail, String hashedPassword){
        User user = null;

        try{
            Query query = entityManager.createQuery("(Select s.id, s.name, s.userType from Student s where s.instEmail = " + instEmail +
                                                    " and s.hashedPassword = " + hashedPassword + ") " +
                                                    "UNION " +
                                                    "(Select p.id, p.name, p.userType from Professor p where p.instEmail = " + instEmail +
                                                    " and p.hashedPassword = " + hashedPassword + ") " +
                                                    "UNION " +
                                                    "(Select a.id, a.name, a.userType from Administrator a where a.instEmail = " + instEmail +
                                                    " and a.hashedPassword = " + hashedPassword + ")");

            user = (User)query.getSingleResult();

            logger.info("User: " + instEmail + " successfully logged in");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return user;
    }

    public List<Course> getCourses(Integer id, Integer userType){
        List<Course> courses = new ArrayList<>();

        try{
            Query query;

            if(userType == 1){
                query = entityManager.createQuery("Select c.courseName from Course c where c.professor.id = " + id);
                courses = query.getResultList();
            }

            else{
                query = entityManager.createQuery("Select c.courseName, c.students from Course c");
                List<Course> allCourses = query.getResultList();
                List<Student> students;

                for(Course course : allCourses){
                    students = course.getStudents();

                    for(Student student : students){
                        if(student.getId().compareTo(id) == 0){
                            courses.add(course);
                            break;
                        }
                    }
                }
            }

            logger.info("Materials successfully obtained");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return courses;
    }

    public List<Material> getMaterials(String courseName){
        List<Material> materials = null;

        try{
            Query query = entityManager.createQuery("Select c.materials from Course c");

            materials = query.getResultList();

            logger.info("Materials successfully obtained");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return materials;
    }
}
