package ejbservices;

import data.Course;
import data.Professor;
import data.Student;

import dto.CourseDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;

import ejbcrud.QueriesCrudLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class AdminBean implements AdminBeanRemote{
    @EJB
    private QueriesCrudLocal queriesCrud;
    private Logger logger;

    public AdminBean(){
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }

    public boolean createStudentAccount(String hashedPassword, String name, Date birth, String instEmail,
                                        String altEmail, String address, Integer telephone, Integer number,
                                        Integer yearOfCourse){

        return queriesCrud.insertStudent(hashedPassword, name, birth, instEmail, altEmail, address, telephone, number,
                                         yearOfCourse);
    }

    public boolean createProfessorAccount(String hashedPassword, String name, Date birth, String instEmail,
                                          String altEmail, String address, Integer telephone, Integer internalNumber,
                                          String category, String office, Integer internalTelephoneNumber,
                                          double salary){

        return queriesCrud.insertProfessor(hashedPassword, name, birth, instEmail, altEmail, address, telephone,
                                           internalNumber, category, office, internalTelephoneNumber, salary);
    }

    public boolean createCourse(String courseName, String professorEmail, List<String> studentsEmail){
        List<Student> students = new ArrayList<>();

        for(String s : studentsEmail){
            students.add(this.queriesCrud.selectStudents(s).get(0));
        }

        Course course = new Course(courseName, this.queriesCrud.selectProfessors(professorEmail).get(0), students);

        return this.queriesCrud.insertCourse(course);
    }

    public boolean editStudent(String hashedPassword, String name, Date birth, String instEmail,
                               String altEmail, String address, Integer telephone, Integer number,
                               Integer yearOfCourse, List<String> coursesName, String newInstEmail){

        return this.queriesCrud.editStudent(hashedPassword, name, birth, instEmail, altEmail, address, telephone,
                                            number, yearOfCourse, coursesName, newInstEmail);
    }

    public boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                                 String altEmail, String address, Integer telephone, Integer internalNumber,
                                 String category, String office, Integer internalTelephoneNumber, double salary,
                                 List<String> coursesName, String newInstEmail){

        return this.queriesCrud.editProfessor(hashedPassword, name, birth, instEmail, altEmail, address, telephone,
                                              internalNumber, category, office, internalTelephoneNumber, salary,
                                              coursesName, newInstEmail);
    }

    public boolean editCourse(String courseName, String professorEmail, List<String> studentsEmail, String newCourseName){
        return this.queriesCrud.editCourse(courseName, professorEmail, studentsEmail, newCourseName);
    }

    public boolean deleteStudent(String instEmail){
        return this.queriesCrud.deleteStudent(instEmail);
    }

    public boolean deleteProfessor(String instEmail){
        return this.queriesCrud.deleteProfessor(instEmail);
    }

    public boolean deleteCourse(String courseName){
        return this.queriesCrud.deleteCourse(courseName);
    }

    public boolean deleteMaterial(String timestamp){
        return this.queriesCrud.deleteMaterial(timestamp);
    }

    public List<StudentDTO> getStudents(){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();

        if((students = this.queriesCrud.selectStudents("%")).size() == 0){
            logger.error("Could not find any student");
        }

        else{
            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Students successfully retrieved");
        }

        return studentsDTO;
    }

    public List<ProfessorDTO> getProfessors(){
        List<Professor> professors;
        List<ProfessorDTO> professorsDTO = new ArrayList<>();

        if((professors = this.queriesCrud.selectProfessors("%")).size() == 0){
            logger.error("Could not find any professor");
        }

        else{
            for(Professor professor : professors){
                professorsDTO.add(new ProfessorDTO(professor));
            }

            logger.info("Professor successfully retrieved");
        }

        return professorsDTO;
    }

    public List<CourseDTO> getCourses(){
        List<Course> courses;
        List<CourseDTO> coursesDTO = new ArrayList<>();

        if((courses = this.queriesCrud.selectCourses("%")).size() == 0){
            logger.error("Could not find any course");
        }

        else{
            for(Course course : courses){
                coursesDTO.add(new CourseDTO(course));
            }

            logger.info("Courses successfully retrieved");
        }


        return coursesDTO;
    }
}