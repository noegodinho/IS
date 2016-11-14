package ejb;

import data.*;

import dto.*;
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

    public UserDTO loginUser(String instEmail, String hashedPassword){
        UserDTO userDTO = null;

        try{
            Query query = entityManager.createQuery("Select s from Student s " +
                                                    "where s.instEmail like ?1 " +
                                                    "and s.hashedPassword like ?2");
            query.setParameter(1, instEmail);
            query.setParameter(2, hashedPassword);

            List<Student> student = query.getResultList();

            if(student.size() == 0){
                query = entityManager.createQuery("Select p from Professor p " +
                        "where p.instEmail like ?1 " +
                        "and p.hashedPassword like ?2");

                query.setParameter(1, instEmail);
                query.setParameter(2, hashedPassword);

                List<Professor> professor = query.getResultList();

                if(professor.size() == 0){
                    query = entityManager.createQuery("Select a from Administrator a " +
                                                        "where a.instEmail like ?1 " +
                                                        "and a.hashedPassword like ?2");

                    query.setParameter(1, instEmail);
                    query.setParameter(2, hashedPassword);

                    List<Administrator> administrator = query.getResultList();

                    if(administrator.size() == 0){
                        logger.error("User not found");
                    }

                    else{
                        userDTO = new AdministratorDTO(administrator.get(0));

                        logger.info("Admin: " + instEmail + " successfully logged in");
                    }
                }

                else{
                    userDTO = new ProfessorDTO(professor.get(0));

                    logger.info("Professor: " + instEmail + " successfully logged in");
                }
            }

            else{
                userDTO = new StudentDTO(student.get(0));

                logger.info("Student: " + instEmail + " successfully logged in");
            }
        }catch(PersistenceException nre){
            logger.info("SQL error");
        }

        return userDTO;
    }

    public List<CourseDTO> getCourses(Integer id, Integer userType){
        List<Course> courses;
        List<CourseDTO> coursesDTO = new ArrayList<>();

        try{
            Query query;

            if(userType == 1){
                query = entityManager.createQuery("Select c from Course c where c.professor.id = ?1");
                query.setParameter(1, id);
                courses = query.getResultList();

                for(Course course : courses){
                    coursesDTO.add(new CourseDTO(course));
                }
            }

            else{
                query = entityManager.createQuery("Select c from Course c");
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
            Query query = entityManager.createQuery("Select m from Material m where m.course.courseName like ?1");
            query.setParameter(1, courseName);

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
