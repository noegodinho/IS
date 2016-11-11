package ejb;

import data.Course;
import data.Student;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface ProfBeanRemote{
    boolean uploadMaterial(String filename, String timestamp, Course course);
    List<Student> getStudentsByCourse(String courseName, boolean ascendingOrder);
    List<Student> searchStudents(String name, Date birth, String instEmail, String altEmail, String address,
                                 Integer telephone, Integer number, Integer yearOfCourse);
}
