package generated;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer number;
    private Integer yearOfCourse;

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
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
