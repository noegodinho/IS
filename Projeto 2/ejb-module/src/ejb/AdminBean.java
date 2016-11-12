package ejb;

import data.Course;
import data.Professor;
import data.Student;
import data.User;

import dto.CourseDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class AdminBean implements AdminBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public AdminBean(){
        this.logger = LoggerFactory.getLogger(AdminBean.class);
    }

    public boolean createStudentAccount(String hashedPassword, String name, Date birth, String instEmail,
                                        String altEmail, String address, Integer telephone, Integer number,
                                        Integer yearOfCourse){
        try{
            User student = new Student(hashedPassword, name, birth, instEmail, altEmail, address, telephone, number,
                                       yearOfCourse);
            entityManager.persist(student);

            logger.info("Student: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean createProfessorAccount(String hashedPassword, String name, Date birth, String instEmail,
                                          String altEmail, String address, Integer telephone, Integer internalNumber,
                                          String category, String office, Integer internalTelephoneNumber,
                                          double salary){
        try{
            User professor = new Professor(hashedPassword, name, birth, instEmail, altEmail, address, telephone,
                                           internalNumber, category, office, internalTelephoneNumber, salary);
            entityManager.persist(professor);

            logger.info("Professor: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean createCourse(String courseName, ProfessorDTO professor, List<StudentDTO> studentsDTO){
        try{
            List<Student> students = new ArrayList<>();

            for(StudentDTO studentDTO : studentsDTO){
                students.add(new Student(studentDTO));
            }

            Course course = new Course(courseName, new Professor(professor), students);

            entityManager.persist(course);

            logger.info("Course: " + courseName + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editStudent(String hashedPassword, String name, Date birth, String instEmail,
                               String altEmail, String address, Integer telephone, Integer number,
                               Integer yearOfCourse, List<CourseDTO> courses, String newInstEmail){
        try{
            Query query = entityManager.createQuery("Select s from Student s where s.instEmail like ?1");
            query.setParameter(1, instEmail);
            Student student = (Student)query.getSingleResult();

            student.setHashedPassword(hashedPassword);
            student.setName(name);
            student.setBirth(birth);
            student.setInstEmail(newInstEmail);
            student.setAltEmail(altEmail);
            student.setAddress(address);
            student.setTelephone(telephone);
            student.setNumber(number);
            student.setYearOfCourse(yearOfCourse);

            List<Course> courseList = new ArrayList<>();

            for(CourseDTO courseDTO : courses){
                courseList.add(new Course(courseDTO));
            }

            student.setCourses(courseList);

            entityManager.persist(student);

            logger.info("Student: " + name + " successfully edited");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editProfessor(String hashedPassword, String name, Date birth, String instEmail,
                                 String altEmail, String address, Integer telephone, Integer internalNumber,
                                 String category, String office, Integer internalTelephoneNumber, double salary,
                                 List<CourseDTO> courses, String newInstEmail){
        try{
            Query query = entityManager.createQuery("Select p from Professor p where p.instEmail like ?1");
            query.setParameter(1, instEmail);
            Professor professor = (Professor)query.getSingleResult();

            professor.setHashedPassword(hashedPassword);
            professor.setName(name);
            professor.setBirth(birth);
            professor.setInstEmail(newInstEmail);
            professor.setAltEmail(altEmail);
            professor.setAddress(address);
            professor.setTelephone(telephone);
            professor.setInternalNumber(internalNumber);
            professor.setCategory(category);
            professor.setOffice(office);
            professor.setInternalTelephoneNumber(internalTelephoneNumber);
            professor.setSalary(salary);

            List<Course> courseList = new ArrayList<>();

            for(CourseDTO courseDTO : courses){
                courseList.add(new Course(courseDTO));
            }

            professor.setCourses(courseList);

            entityManager.persist(professor);

            logger.info("Professor: " + name + " successfully edited");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editCourse(String courseName, ProfessorDTO professor, List<StudentDTO> students, String newCourseName){
        try{
            Query query = entityManager.createQuery("Select c from Course c where c.courseName like ?1");
            query.setParameter(1, courseName);
            Course course = (Course)query.getSingleResult();

            course.setCourseName(newCourseName);
            course.setProfessor(new Professor(professor));

            List<Student> studentList = new ArrayList<>();

            for(StudentDTO studentDTO : students){
                studentList.add(new Student(studentDTO));
            }

            course.setStudents(studentList);

            entityManager.persist(course);

            logger.info("Course: " + courseName + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteStudent(String instEmail){
        try{
            Query query = entityManager.createQuery("Delete from Student s where s.instEmail like ?1");
            query.setParameter(1, instEmail);
            query.executeUpdate();

            logger.info("Student: " + instEmail + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteProfessor(String instEmail){
        try{
            Query query = entityManager.createQuery("Delete from Professor p where p.instEmail like ?1");
            query.setParameter(1, instEmail);
            query.executeUpdate();

            logger.info("Professor: " + instEmail + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteCourse(String courseName){
        try{
            Query query = entityManager.createQuery("Delete from Course c where c.courseName like ?1");
            query.setParameter(1, courseName);
            query.executeUpdate();

            logger.info("Course: " + courseName + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteMaterial(String timestamp){
        try{
            Query query = entityManager.createQuery("Delete from Material m where m.timestamp like ?1");
            query.setParameter(1, timestamp);
            query.executeUpdate();

            logger.info("Material: " + timestamp + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public List<StudentDTO> getStudents(){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select s.instEmail, s.name from Student s");

            students = query.getResultList();

            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Students successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return studentsDTO;
    }

    public List<ProfessorDTO> getProfessors(){
        List<Professor> professors;
        List<ProfessorDTO> professorsDTO = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select p.instEmail, p.name from Professor p");

            professors = query.getResultList();

            for(Professor professor : professors){
                professorsDTO.add(new ProfessorDTO(professor));
            }

            logger.info("Professors successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return professorsDTO;
    }
}