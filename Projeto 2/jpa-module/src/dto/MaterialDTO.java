package dto;

import data.Course;
import data.Material;

import java.io.Serializable;

public class MaterialDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String filename;
    private String timestamp;
    private CourseDTO course;

    public MaterialDTO(){

    }

    public MaterialDTO(Material material){
        this.id = material.getId();
        this.filename = material.getFilename();
        this.timestamp = material.getTimestamp();
        this.setCourse(material.getCourse());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public Course getCourse() {
        return new Course(this.course);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCourse(Course course) {
        this.course = new CourseDTO(course);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
