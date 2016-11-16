package dto;

import data.Course;

import java.io.Serializable;

public class CourseDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String courseName;

    public CourseDTO(){

    }

    public CourseDTO(Course course){
        this.id = course.getId();
        this.courseName = course.getCourseName();
    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
