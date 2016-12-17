package ejbcrud;

import data.*;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Local
public interface QueriesCrudLocal{
    List<String> selectAdminsEmail();
    List<Administrator> selectAdmins(String instEmail);
    List<Professor> selectProfessors(String instEmail);
    List<Student> selectStudents(String instEmail);
    List<Student> searchSpecificStudent(String toQuery, Map<Integer, Object> hashmap);
    List<Course> selectCourses(String courseName);
    List<Material> selectMaterials(String timestamp, String courseName);
    boolean insertAdmin(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                        String address, Integer telephone);
    boolean insertStudent(String hashedPassword, String name, Date birth, String instEmail,
                          String altEmail, String address, Integer telephone, Integer number,
                          Integer yearOfCourse);
    boolean insertProfessor(String hashedPassword, String name, Date birth, String instEmail,
                            String altEmail, String address, Integer telephone, Integer internalNumber,
                            String category, String office, Integer internalTelephoneNumber,
                            double salary);
    boolean insertCourse(Course course);
    boolean insertMaterial(Material material);
    boolean editStudent(String hashedPassword, String name, Date birth, String instEmail,
                        String altEmail, String address, Integer telephone, Integer number,
                        Integer yearOfCourse, List<String> coursesName, String newInstEmail);
    boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                          String altEmail, String address, Integer telephone, Integer internalNumber,
                          String category, String office, Integer internalTelephoneNumber, double salary,
                          List<String> coursesName, String newInstEmail);
    boolean editCourse(String courseName, String professorEmail, List<String> studentsEmail, String newCourseName);
    boolean deleteAdmin(String instEmail);
    boolean deleteProfessor(String instEmail);
    boolean deleteStudent(String instEmail);
    boolean deleteCourse(String courseName);
    boolean deleteMaterial(String timestamp);
}
