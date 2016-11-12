package dto;

import java.util.Date;
import java.util.List;

public class ProfessorDTO{
    private Integer id;
    private String hashedPassword;
    private String name;
    private Date birth;
    private String instEmail;
    private String altEmail;
    private String address;
    private Integer telephone;
    private Integer internalNumber;
    private String category;
    private String office;
    private Integer internalTelephoneNumber;
    private double salary;
    private List<CourseDTO> courses;

    public ProfessorDTO(){
    }

    public ProfessorDTO(Integer id, String hashedPassword, String name, Date birth, String instEmail,
                        String altEmail , String address, Integer telephone,
                        Integer internalNumber, String category, String office, Integer internalTelephoneNumber, double salary){
        this.id = id;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.birth = birth;
        this.instEmail = instEmail;
        this.altEmail = altEmail;
        this.address = address;
        this.telephone = telephone;
        this.internalNumber = internalNumber;
        this.category = category;
        this.office = office;
        this.internalTelephoneNumber = internalTelephoneNumber;
        this.salary = salary;
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

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
