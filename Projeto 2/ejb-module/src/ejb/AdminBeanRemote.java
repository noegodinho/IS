package ejb;

import data.Course;
import data.Professor;
import data.Student;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

@Remote
public interface AdminBeanRemote{
    boolean createStudentAccount(String hashedPassword, String name, Date birth, String instEmail,
                                 String altEmail, String address, Integer telephone, Integer number,
                                 Integer yearOfCourse);
    boolean createProfessorAccount(String hashedPassword, String name, Date birth, String instEmail,
                                   String altEmail, String address, Integer telephone, Integer internalNumber,
                                   String category, String office, Integer internalTelephoneNumber, double salary);
    boolean createCourse(String courseName, Professor professor, List<Student> students);
    boolean editStudent(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                        String address, Integer telephone, Integer number, Integer yearOfCourse, List<Course> courses,
                        String newInstEmail);
    boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                          String altEmail, String address, Integer telephone, Integer internalNumber,
                          String category, String office, Integer internalTelephoneNumber, double salary,
                          List<Course> courses, String newInstEmail);
    boolean editCourse(String courseName, Professor professor, List<Student> students, String newCourseName);
    boolean deleteUser(String instEmail);
    boolean deleteCourse(String courseName);
    boolean deleteMaterial(String filename);
    List<Student> getStudents();
    List<Professor> getProfessors();
}
