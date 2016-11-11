package ejb;

import data.*;

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
            Query query = entityManager.createQuery("Select s.id, s.name, s.userType from Student s " +
                                                    "where s.instEmail like ?1 " +
                                                    "and s.hashedPassword like ?2");
            query.setParameter(1, instEmail);
            query.setParameter(2, hashedPassword);

            user = (Student)query.getSingleResult();

            logger.info("Student: " + instEmail + " successfully logged in");
        }catch(NoResultException nre){
            try{
                Query query = entityManager.createQuery("Select p.id, p.name, p.userType from Professor p " +
                                                        "where p.instEmail like ?1 " +
                                                        "and p.hashedPassword like ?2");

                query.setParameter(1, instEmail);
                query.setParameter(2, hashedPassword);

                user = (Professor)query.getSingleResult();

                logger.info("Professor: " + instEmail + " successfully logged in");
            }catch(NoResultException nre1){
                try{
                    Query query = entityManager.createQuery("Select a.id, a.name, a.userType from Administrator a " +
                                                            "where a.instEmail like ?1 " +
                                                            "and a.hashedPassword like ?2");

                    query.setParameter(1, instEmail);
                    query.setParameter(2, hashedPassword);

                    user = (Administrator)query.getSingleResult();

                    logger.info("Admin: " + instEmail + " successfully logged in");
                }catch(NoResultException nre2){
                    logger.error("SQL error");
                }
            }
        }

        return user;
    }

    public List<Course> getCourses(Integer id, Integer userType){
        List<Course> courses = new ArrayList<>();

        try{
            Query query;

            if(userType == 1){
                query = entityManager.createQuery("Select c.courseName from Course c where c.professor.id = ?1");
                query.setParameter(1, id);
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
