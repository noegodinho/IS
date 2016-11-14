package dto;

import data.Course;
import data.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentDTO extends UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer number;
    private Integer yearOfCourse;
    private List<CourseDTO> courses;

    public StudentDTO(){

    }

    public StudentDTO(Student student){
        super(student.getId(), student.getHashedPassword(), student.getName(), student.getBirth(),
                student.getInstEmail(), student.getAltEmail(), student.getAddress(), student.getTelephone());
        this.number = student.getNumber();
        this.yearOfCourse = student.getYearOfCourse();
        this.courses = student.getCourses();
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

    public List<Course> getCourses() {
        List<Course> courseList = new ArrayList<>();

        for(CourseDTO courseDTO : this.courses){
            courseList.add(new Course(courseDTO));
        }

        return courseList;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
