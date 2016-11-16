package ejbservices;

import data.*;

import dto.*;

import ejbcrud.QueriesCrudRemote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClientBean implements ClientBeanRemote{
    @EJB
    private QueriesCrudRemote queriesCrud;
    private Logger logger;

    public ClientBean(){
        this.logger = LoggerFactory.getLogger(ClientBean.class);
    }

    public UserDTO loginUser(String instEmail, String hashedPassword){
        UserDTO userDTO = null;

        List<Student> student = this.queriesCrud.selectStudents(instEmail);

        if(student.size() != 0 && student.get(0).getHashedPassword().equals(hashedPassword)){
            userDTO = new StudentDTO(student.get(0));
            logger.info("Student: " + instEmail + " successfully logged in");
        }

        else{
            List<Professor> professor = this.queriesCrud.selectProfessors(instEmail);

            if(professor.size() != 0 && professor.get(0).getHashedPassword().equals(hashedPassword)){
                userDTO = new ProfessorDTO(professor.get(0));
                logger.info("Professor: " + instEmail + " successfully logged in");
            }

            else{
                List<Administrator> administrator = this.queriesCrud.selectAdmins(instEmail);

                if(administrator.size() != 0 && administrator.get(0).getHashedPassword().equals(hashedPassword)){
                    userDTO = new AdministratorDTO(administrator.get(0));
                    logger.info("Admin: " + instEmail + " successfully logged in");
                }

                else{
                    logger.error("User not found");
                }
            }
        }

        return userDTO;
    }

    public List<CourseDTO> getCourses(Integer id, Integer userType){
        List<Course> courses;
        List<CourseDTO> coursesDTO = new ArrayList<>();

        courses = this.queriesCrud.selectCourses("%");

        if(userType == 1 && courses.size() != 0){
            for(Course course : courses){
                if(course.getProfessor().getId().compareTo(id) == 0){
                    coursesDTO.add(new CourseDTO(course));
                }
            }

            logger.info("Courses successfully obtained");
        }

        else if(userType == 2 && courses.size() != 0){
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

            logger.info("Courses successfully obtained");
        }

        else{
            logger.error("No courses found");
        }

        return coursesDTO;
    }

    public List<MaterialDTO> getMaterials(String courseName){
        List<Material> materials;
        List<MaterialDTO> materialsDTO = new ArrayList<>();

        materials = this.queriesCrud.selectMaterials("%", courseName);

        if(materials.size() != 0){
            for(Material material : materials){
                materialsDTO.add(new MaterialDTO(material));
            }

            logger.info("Materials successfully obtained");
        }

        else{
            logger.error("No materials found");
        }

        return materialsDTO;
    }
}
