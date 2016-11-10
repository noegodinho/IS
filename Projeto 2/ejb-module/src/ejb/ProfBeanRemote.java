package ejb;

import data.Course;
import data.Student;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProfBeanRemote{
    boolean deleteMaterial(String filename);
    boolean updateMaterial(String filename, Course course);
    List<Student> getStudentsByCourse(String courseName, boolean ascendingOrder);
}
