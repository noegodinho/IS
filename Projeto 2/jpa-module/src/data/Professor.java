package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Professor extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    private int internalNumber;
    private String category;
    private String office;
    private int internalTelephoneNumber;
    private float salary;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

    public Professor(){
        super();
    }

    public Professor(String name, Date birth, String instEmail, String password, String altEmail , String address, int telephone,
                     int internalNumber, String category, String office, int internalTelephoneNumber, float salary) {
        super(name,birth,instEmail,password,altEmail,address,telephone);
        this.internalNumber = internalNumber;
        this.category = category;
        this.office = office;
        this.internalTelephoneNumber = internalTelephoneNumber;
        this.salary = salary;
    }

    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
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

    public int getInternalTelephoneNumber() {
        return internalTelephoneNumber;
    }

    public void setInternalTelephoneNumber(int internalTelephoneNumber) {
        this.internalTelephoneNumber = internalTelephoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
