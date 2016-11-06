package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Student extends User implements Serializable {
    private int number;
    private int yearOfCourse;

    @ManyToMany
    protected List<Course> courses;


    public Student(String name, Date birth, String instEmail, String password, String altEmail , String address, int telephone,
                     int number, int yearOfCourse) {
        super(name,birth,instEmail,password,altEmail,address,telephone);
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
