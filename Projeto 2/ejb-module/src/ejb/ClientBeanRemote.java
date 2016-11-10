package ejb;

import data.Course;
import data.Material;
import data.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ClientBeanRemote{
    User loginUser(String instEmail, String hashedPassword);
    List<Course> getCourses(Integer id, Integer userType);
    List<Material> getMaterials(String courseName);
}
