package ejb;

import dto.CourseDTO;
import dto.MaterialDTO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ClientBeanRemote{
    Object loginUser(String instEmail, String hashedPassword);
    List<CourseDTO> getCourses(Integer id, Integer userType);
    List<MaterialDTO> getMaterials(String courseName);
}
