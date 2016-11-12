package ejb;

import data.*;

import dto.CourseDTO;
import dto.MaterialDTO;
import dto.StudentDTO;
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

    public Object loginUser(String instEmail, String hashedPassword){
        Object user = null;

        try{
            Query query = entityManager.createQuery("Select s.id, s.name from Student s " +
                                                    "where s.instEmail like ?1 " +
                                                    "and s.hashedPassword like ?2");
            query.setParameter(1, instEmail);
            query.setParameter(2, hashedPassword);

            user = (StudentDTO)query.getSingleResult();

            logger.info("Student: " + instEmail + " successfully logged in");
        }catch(NoResultException nre){
            try{
                Query query = entityManager.createQuery("Select p.id, p.name from Professor p " +
                                                        "where p.instEmail like ?1 " +
                                                        "and p.hashedPassword like ?2");

                query.setParameter(1, instEmail);
                query.setParameter(2, hashedPassword);

                user = (Professor)query.getSingleResult();

                logger.info("Professor: " + instEmail + " successfully logged in");
            }catch(NoResultException nre1){
                try{
                    Query query = entityManager.createQuery("Select a.id, a.name from Administrator a " +
                                                            "where a.instEmail like ?1 " +
                                                            "and a.hashedPassword like ?2");

                    query.setParameter(1, instEmail);
                    query.setParameter(2, hashedPassword);

                    user = (Administrator)query.getSingleResult();

                    logger.info("Admin: " + instEmail + " successfully logged in");
                }catch(NoResultException nre2){
                    logger.error("User not found");
                }
            }
        }

        return user;
    }

    public List<CourseDTO> getCourses(Integer id, Integer userType){
        List<Course> courses;
        List<CourseDTO> coursesDTO = new ArrayList<>();

        try{
            Query query;

            if(userType == 1){
                query = entityManager.createQuery("Select c.courseName from Course c where c.professor.id = ?1");
                query.setParameter(1, id);
                courses = query.getResultList();

                for(Course course : courses){
                    coursesDTO.add(new CourseDTO(course));
                }
            }

            else{
                query = entityManager.createQuery("Select c.courseName, c.students from Course c");
                courses = query.getResultList();
                List<Student> students;

                for(Course course : courses){
                    students = course.getStudents();

                    for(Student student : students){
                        if(student.getId().compareTo(id) == 0){
                            coursesDTO.add(new CourseDTO(course));
                            break;
                        }
                    }
                }
            }

            logger.info("Materials successfully obtained");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return coursesDTO;
    }

    public List<MaterialDTO> getMaterials(String courseName){
        List<Material> materials;
        List<MaterialDTO> materialsDTO = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select c.materials from Course c");

            materials = query.getResultList();

            for(Material material : materials){
                materialsDTO.add(new MaterialDTO(material));
            }

            logger.info("Materials successfully obtained");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return materialsDTO;
    }
}
