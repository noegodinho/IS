package ejb;

import data.Course;
import data.Professor;
import data.Student;
import dto.CourseDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;

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
    boolean createCourse(String courseName, Professor professor, List<StudentDTO> studentsDTO);
    boolean editStudent(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                        String address, Integer telephone, Integer number, Integer yearOfCourse, List<CourseDTO> courses,
                        String newInstEmail);
    boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                          String altEmail, String address, Integer telephone, Integer internalNumber,
                          String category, String office, Integer internalTelephoneNumber, double salary,
                          List<CourseDTO> courses, String newInstEmail);
    boolean editCourse(String courseName, ProfessorDTO professor, List<StudentDTO> students, String newCourseName);
    boolean deleteStudent(String instEmail);
    boolean deleteProfessor(String instEmail);
    boolean deleteCourse(String courseName);
    boolean deleteMaterial(String filename);
    List<StudentDTO> getStudents();
    List<ProfessorDTO> getProfessors();
}
