package dto;

import data.Course;
import data.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDTO{
    private Integer id;
    private String hashedPassword;
    private String name;
    private Date birth;
    private String instEmail;
    private String altEmail;
    private String address;
    private Integer telephone;
    private Integer number;
    private Integer yearOfCourse;
    private List<CourseDTO> courses;

    public StudentDTO(){

    }

    public StudentDTO(Student student){
        this.id = student.getId();
        this.hashedPassword = student.getHashedPassword();
        this.name = student.getName();
        this.birth = student.getBirth();
        this.instEmail = student.getInstEmail();
        this.altEmail = student.getAltEmail();
        this.address = student.getAddress();
        this.telephone = student.getTelephone();
        this.number = student.getNumber();
        this.yearOfCourse = student.getYearOfCourse();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getInstEmail() {
        return instEmail;
    }

    public void setInstEmail(String instEmail) {
        this.instEmail = instEmail;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
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

    public void setCourses(List<Course> courses) {
        this.courses = new ArrayList<>();

        for(Course course : courses){
            this.courses.add(new CourseDTO(course));
        }
    }
}
