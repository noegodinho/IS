package ejb;

import data.Course;
import data.Material;
import data.Student;

import dto.CourseDTO;
import dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.*;

@Stateless
public class ProfBean implements ProfBeanRemote{
    @PersistenceContext
    private EntityManager entityManager;
    private Logger logger;

    public ProfBean(){
        this.logger = LoggerFactory.getLogger(ProfBean.class);
    }

    public boolean uploadMaterial(String filename, String timestamp, String courseName){
        try{
            Query query = entityManager.createQuery("Select c from Course c where c.courseName like ?1");
            query.setParameter(1, courseName);
            Material material = new Material(filename, timestamp, (Course)query.getResultList().get(0));
            entityManager.persist(material);

            logger.info("Material: " + filename + " successfully uploaded");
        }catch(PersistenceException pe){
            logger.error("SQL error");
            return false;
        }

        return true;
    }

    public List<StudentDTO> getStudentsByCourse(String courseName, boolean ascendingOrder){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();

        try{
            Query query = entityManager.createQuery("Select c from Course c where c.courseName like ?1");
            query.setParameter(1, courseName);

            students = query.getResultList();

            if(ascendingOrder){
                students.sort((Student s1, Student s2) -> s1.getName().compareTo(s2.getName()));
            }

            else{
                students.sort((Student s1, Student s2) -> s2.getName().compareTo(s1.getName()));
            }

            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Students from Course: " + courseName + " successfully returned");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return studentsDTO;
    }

    public List<StudentDTO> searchStudents(String name, Date birth, String instEmail, String altEmail, String address,
                                        Integer telephone, Integer number, Integer yearOfCourse){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        Integer counter = 0;

        try{
            String toQuery = "Select s from Student s where ";

            if(!name.isEmpty()){
                toQuery += "s.name like ?" + counter + " and ";
                map.put(++counter, name);
            }

            if(!birth.toString().isEmpty()){
                toQuery += "s.birth like ?" + counter + " and ";
                map.put(++counter, birth.toString());
            }

            if(!instEmail.isEmpty()){
                toQuery += "s.instEmail like ?" + counter + " and ";
                map.put(++counter, instEmail);
            }

            if(!altEmail.isEmpty()){
                toQuery += "s.altEmail like ?" + counter + " and ";
                map.put(++counter, altEmail);
            }

            if(!address.isEmpty()){
                toQuery += "s.address like ?" + counter + " and ";
                map.put(++counter, address);
            }

            if(!telephone.toString().isEmpty()){
                toQuery += "s.telephone = ?" + counter + " and ";
                map.put(++counter, telephone.toString());
            }

            if(!number.toString().isEmpty()){
                toQuery += "s.number = ?" + counter + " and ";
                map.put(++counter, number.toString());
            }

            if(!yearOfCourse.toString().isEmpty()){
                toQuery += "s.yearOfCourse = ?" + counter;
                map.put(++counter, telephone.toString());
            }

            if(toQuery.substring(toQuery.length() - 5, toQuery.length()).equals("and ")){
                toQuery = toQuery.substring(0, toQuery.length() - 5);
            }

            Query query = entityManager.createQuery(toQuery);

            for(Map.Entry<Integer, String> entry : map.entrySet()){
                query.setParameter(entry.getKey(), entry.getValue());
            }

            students = query.getResultList();

            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Student successfully retrieved");
        }catch(PersistenceException pe){
            logger.error("SQL error");
        }

        return studentsDTO;
    }
}
