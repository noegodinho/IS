package dto;

import data.Student;

import java.io.Serializable;

public class StudentDTO extends UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer number;
    private Integer yearOfCourse;

    public StudentDTO(){

    }

    public StudentDTO(Student student){
        super(student.getId(), student.getHashedPassword(), student.getName(), student.getBirth(),
                student.getInstEmail(), student.getAltEmail(), student.getAddress(), student.getTelephone());
        this.number = student.getNumber();
        this.yearOfCourse = student.getYearOfCourse();
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
}
