package data;

import dto.CourseDTO;
import dto.StudentDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(unique=true, nullable=false)
    private Integer number;
    @Column(nullable=false)
    private Integer yearOfCourse;

    @ManyToMany
    private List<Course> courses;

    public Student(){
        super();
    }

    public Student(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                   String address, Integer telephone, Integer number, Integer yearOfCourse){
        super(hashedPassword, name, birth, instEmail, altEmail, address, telephone);
        this.number = number;
        this.yearOfCourse = yearOfCourse;
    }

    public Student(StudentDTO studentDTO){
        super(studentDTO.getId(), studentDTO.getHashedPassword(), studentDTO.getName(), studentDTO.getBirth(), studentDTO.getInstEmail(),
                studentDTO.getAltEmail(), studentDTO.getAddress(), studentDTO.getTelephone());
        this.number = studentDTO.getNumber();
        this.yearOfCourse = studentDTO.getYearOfCourse();
        this.courses = studentDTO.getCourses();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getYearOfCourse() {
        return yearOfCourse;
    }

    public void setYearOfCourse(Integer yearOfCourse) {
        this.yearOfCourse = yearOfCourse;
    }

    public List<CourseDTO> getCourses() {
        List<CourseDTO> courseList = new ArrayList<>();

        for(Course course : this.courses){
            courseList.add(new CourseDTO(course));
        }

        return courseList;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
