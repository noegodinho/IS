package ejbcrud;

import data.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Stateless
public class QueriesCrud implements QueriesCrudLocal{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public QueriesCrud(){
        this.logger = LoggerFactory.getLogger(QueriesCrud.class);
    }

    public List<String> selectAdminsEmail(){
        List<String> admins = new ArrayList<>();

        try{
            List<Administrator> administrators = this.selectAdmins("%");

            for(Administrator administrator : administrators){
                admins.add(administrator.getInstEmail());
            }

            logger.info("Admin list successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return admins;
    }

    public List<Administrator> selectAdmins(String instEmail){
        List<Administrator> administrators = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select a from Administrator a where a.instEmail like ?1");
            query.setParameter(1, instEmail);

            administrators = query.getResultList();
            logger.info("Admin: " + instEmail + " successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return administrators;
    }

    public List<Professor> selectProfessors(String instEmail){
        List<Professor> professors = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select p from Professor p where p.instEmail like ?1");
            query.setParameter(1, instEmail);

            professors = query.getResultList();
            logger.info("Professor: " + instEmail + " successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return professors;
    }

    public List<Student> selectStudents(String instEmail){
        List<Student> students = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select s from Student s where s.instEmail like ?1");
            query.setParameter(1, instEmail);

            students = query.getResultList();
            logger.info("Student: " + instEmail + " successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return students;
    }

    public List<Student> searchSpecificStudent(String toQuery, Map<Integer, Object> hashmap){
        Query query = entityManager.createQuery(toQuery);

        for(Map.Entry<Integer, Object> entry : hashmap.entrySet()){
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    public List<Course> selectCourses(String courseName){
        List<Course> courses = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select c from Course c where c.courseName like ?1");
            query.setParameter(1, courseName);

            courses = query.getResultList();
            logger.info("Course: " + courseName + " successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return courses;
    }

    public List<Material> selectMaterials(String timestamp, String courseName){
        List<Material> materials = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select m from Material m where m.timestamp like ?1 and m.course.courseName like ?2");
            query.setParameter(1, timestamp);
            query.setParameter(2, courseName);

            materials = query.getResultList();
            logger.info("Material: " + timestamp + " successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return materials;
    }

    public boolean insertAdmin(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                            String address, Integer telephone){
        try{
            Administrator administrator = new Administrator(hashedPassword, name, birth, instEmail, altEmail, address,
                    telephone);

            entityManager.persist(administrator);
            logger.info("Admin: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean insertProfessor(String hashedPassword, String name, Date birth, String instEmail,
                                   String altEmail, String address, Integer telephone, Integer internalNumber,
                                   String category, String office, Integer internalTelephoneNumber,
                                   double salary){
        try{
            Professor professor = new Professor(hashedPassword, name, birth, instEmail, altEmail, address, telephone,
                    internalNumber, category, office, internalTelephoneNumber, salary);
            entityManager.persist(professor);

            logger.info("Professor: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean insertStudent(String hashedPassword, String name, Date birth, String instEmail,
                                 String altEmail, String address, Integer telephone, Integer number,
                                 Integer yearOfCourse){
        try{
            Student student = new Student(hashedPassword, name, birth, instEmail, altEmail, address, telephone, number,
                                          yearOfCourse);
            entityManager.persist(student);

            logger.info("Student: " + name + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean insertCourse(Course course){
        try{
            entityManager.persist(course);

            logger.info("Course: " + course.getCourseName() + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean insertMaterial(Material material){
        try{
            entityManager.persist(material);

            logger.info("Material: " + material.getTimestamp() + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean editStudent(String hashedPassword, String name, Date birth, String instEmail,
                               String altEmail, String address, Integer telephone, Integer number,
                               Integer yearOfCourse, List<String> coursesName, String newInstEmail){
        try{
            Student student = this.selectStudents(instEmail).get(0);

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

            for(String s : coursesName){
                courseList.add(this.selectCourses(s).get(0));
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
                                 List<String> coursesName, String newInstEmail){
        try{
            Professor professor = this.selectProfessors(instEmail).get(0);

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

            for(String s : coursesName){
                courseList.add(this.selectCourses(s).get(0));
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

    public boolean editCourse(String courseName, String professorEmail, List<String> studentsEmail, String newCourseName){
        try{
            Course course = this.selectCourses(courseName).get(0);

            course.setCourseName(newCourseName);

            List<Student> students = new ArrayList<>();

            for(String s : studentsEmail){
                students.add(this.selectStudents(s).get(0));
            }

            course.setProfessor(this.selectProfessors(professorEmail).get(0));
            course.setStudents(students);

            entityManager.persist(course);

            logger.info("Course: " + courseName + " successfully registered");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteAdmin(String instEmail){
        Administrator administrator;

        try{
            administrator = this.selectAdmins(instEmail).get(0);

            entityManager.remove(administrator);
            logger.info("Admin: " + instEmail + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteProfessor(String instEmail){
        Professor professor;

        try{
            professor = this.selectProfessors(instEmail).get(0);

            entityManager.remove(professor);
            logger.info("Professor: " + instEmail + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteStudent(String instEmail){
        Student student;

        try{
            student = this.selectStudents(instEmail).get(0);

            entityManager.remove(student);
            logger.info("Student: " + instEmail + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteCourse(String courseName){
        Course course;

        try{
            course = this.selectCourses(courseName).get(0);

            entityManager.remove(course);
            logger.info("Course: " + courseName + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public boolean deleteMaterial(String timestamp){
        Material material;

        try{
            material = this.selectMaterials(timestamp, "%").get(0);

            entityManager.remove(material);
            logger.info("Material: " + timestamp + " successfully removed");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }
}
