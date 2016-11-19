package ejbservices;

import data.Material;
import data.Student;

import dto.StudentDTO;

import ejbcrud.QueriesCrudLocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;

@Stateless
public class ProfBean implements ProfBeanRemote{
    @EJB
    private QueriesCrudLocal queriesCrud;
    private Logger logger;

    public ProfBean(){
        this.logger = LoggerFactory.getLogger(ProfBean.class);
    }

    public boolean uploadMaterial(String filename, String timestamp, String courseName){
        Material material = new Material(filename, timestamp, this.queriesCrud.selectCourses(courseName).get(0));

        return this.queriesCrud.insertMaterial(material);
    }

    public List<StudentDTO> getStudentsByCourse(String courseName, boolean ascendingOrder){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();

        students = this.queriesCrud.selectCourses(courseName).get(0).getStudents();

        if(students != null){
            if(ascendingOrder){
                students.sort((Student s1, Student s2) -> s1.getName().compareTo(s2.getName()));
            }

            else{
                students.sort((Student s1, Student s2) -> s2.getName().compareTo(s1.getName()));
            }

            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Students from course: " + courseName + " retrieved successfully");
        }

        return studentsDTO;
    }

    public List<StudentDTO> searchStudents(String name, Date birth, String instEmail, String altEmail, String address,
                                        Integer telephone, Integer number, Integer yearOfCourse){
        List<Student> students;
        List<StudentDTO> studentsDTO = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<>();
        Integer counter = 1;

        String toQuery = "Select s from Student s where ";

        if(!name.isEmpty()){
            toQuery += "s.name like ?" + counter + " and ";
            map.put(counter++, name);
        }

        if(!birth.toString().isEmpty()){
            toQuery += "s.birth like ?" + counter + " and ";
            map.put(counter++, birth);
        }

        if(!instEmail.isEmpty()){
            toQuery += "s.instEmail like ?" + counter + " and ";
            map.put(counter++, instEmail);
        }

        if(!altEmail.isEmpty()){
            toQuery += "s.altEmail like ?" + counter + " and ";
            map.put(counter++, altEmail);
        }

        if(!address.isEmpty()){
            toQuery += "s.address like ?" + counter + " and ";
            map.put(counter++, address);
        }

        if(!telephone.toString().isEmpty()){
            toQuery += "s.telephone = ?" + counter + " and ";
            map.put(counter++, telephone);
        }

        if(!number.toString().isEmpty()){
            toQuery += "s.number = ?" + counter + " and ";
            map.put(counter++, number);
        }

        if(!yearOfCourse.toString().isEmpty()){
            toQuery += "s.yearOfCourse = ?" + counter;
            map.put(counter, telephone);
        }

        if(toQuery.substring(toQuery.length() - 5, toQuery.length()).equals("and ")){
            toQuery = toQuery.substring(0, toQuery.length() - 5);
        }

        if((students = this.queriesCrud.searchSpecificStudent(toQuery, map)).size() == 0){
            logger.info("No student found");
        }

        else{
            for(Student student : students){
                studentsDTO.add(new StudentDTO(student));
            }

            logger.info("Student successfully retrieved");
        }

        return studentsDTO;
    }
}
