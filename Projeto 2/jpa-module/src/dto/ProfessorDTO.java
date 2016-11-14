package dto;

import data.Course;
import data.Professor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDTO extends UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;
    private Integer internalNumber;
    private String category;
    private String office;
    private Integer internalTelephoneNumber;
    private double salary;
    private List<CourseDTO> courses;

    public ProfessorDTO(){
    }

    public ProfessorDTO(Professor professor){
        super(professor.getId(), professor.getHashedPassword(), professor.getName(), professor.getBirth(),
                professor.getInstEmail(), professor.getAltEmail(), professor.getAddress(),
                professor.getTelephone());
        this.internalNumber = professor.getInternalNumber();
        this.category = professor.getCategory();
        this.office = professor.getOffice();
        this.internalTelephoneNumber = professor.getInternalTelephoneNumber();
        this.salary = professor.getSalary();
        this.courses = professor.getCourses();
    }

    public Integer getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(Integer internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getInternalTelephoneNumber() {
        return internalTelephoneNumber;
    }

    public void setInternalTelephoneNumber(Integer internalTelephoneNumber) {
        this.internalTelephoneNumber = internalTelephoneNumber;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
