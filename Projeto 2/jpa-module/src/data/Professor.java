package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Professor extends User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column(unique=true, nullable=false)
    private Integer internalNumber;
    @Column(nullable=false)
    private String category;
    @Column(nullable=false)
    private String office;
    @Column(unique=true, nullable=false)
    private Integer internalTelephoneNumber;
    @Column(nullable=false)
    private double salary;

    @OneToMany(mappedBy = "professor")
    private List<Course> courses;

    public Professor(){
        super();
    }

    public Professor(String hashedPassword, String name, Date birth, String instEmail,
                     String altEmail , String address, Integer telephone,
                     Integer internalNumber, String category, String office, Integer internalTelephoneNumber, double salary){
        super(hashedPassword, name, birth, instEmail, altEmail, address, telephone);
        this.internalNumber = internalNumber;
        this.category = category;
        this.office = office;
        this.internalTelephoneNumber = internalTelephoneNumber;
        this.salary = salary;
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
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
