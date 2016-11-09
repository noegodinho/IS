package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Student extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(unique=true, nullable=false)
    private int number;
    @Column(nullable=false)
    private int yearOfCourse;

    @ManyToMany
    private List<Course> courses;

    public Student(){
        super();
    }

    public Student(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                   String address, int telephone, int userType, int number, int yearOfCourse){
        super(hashedPassword, name, birth, instEmail, altEmail, address, telephone, userType);
        this.number = number;
        this.yearOfCourse = yearOfCourse;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYearOfCourse() {
        return yearOfCourse;
    }

    public void setYearOfCourse(int yearOfCourse) {
        this.yearOfCourse = yearOfCourse;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
