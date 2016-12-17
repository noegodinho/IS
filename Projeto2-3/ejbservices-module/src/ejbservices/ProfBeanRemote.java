package ejbservices;

import dto.StudentDTO;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ProfBeanRemote{
    boolean uploadMaterial(String filename, String timestamp, String courseName);
    List<StudentDTO> getStudentsByCourse(String courseName, boolean ascendingOrder);
    List<StudentDTO> searchStudents(String name, Date birth, String instEmail, String altEmail, String address,
                                 Integer telephone, Integer number, Integer yearOfCourse);
}
