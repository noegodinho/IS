package dto;

import data.Course;
import data.Material;

public class MaterialDTO{
    private Integer id;
    private String filename;
    private String timestamp;
    private Course course;

    public MaterialDTO(){

    }

    public MaterialDTO(Material material){
        this.id = material.getId();
        this.filename = material.getFilename();
        this.timestamp = material.getTimestamp();
        this.course = material.getCourse();
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
        return course;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
