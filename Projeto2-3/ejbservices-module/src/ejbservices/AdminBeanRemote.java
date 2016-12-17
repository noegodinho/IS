package ejbservices;

import dto.CourseDTO;
import dto.MaterialDTO;
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
    boolean createCourse(String courseName, String professorEmail, List<String> studentsEmail);
    boolean editStudent(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                        String address, Integer telephone, Integer number, Integer yearOfCourse, List<String> coursesName,
                        String newInstEmail);
    boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                          String altEmail, String address, Integer telephone, Integer internalNumber,
                          String category, String office, Integer internalTelephoneNumber, double salary,
                          List<String> coursesName, String newInstEmail);
    boolean editCourse(String courseName, String professorEmail, List<String> studentsEmail, String newCourseName);
    boolean deleteStudent(String instEmail);
    boolean deleteProfessor(String instEmail);
    boolean deleteCourse(String courseName);
    boolean deleteMaterial(String filename);
    List<StudentDTO> getStudents();
    List<ProfessorDTO> getProfessors();
    List<CourseDTO> getCourses();
    List<MaterialDTO> getMaterials(String courseName);
}
