package data;

import dto.CourseDTO;
import dto.MaterialDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Material implements Serializable{
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable=false)
    private String filename;
    @Column(unique=true, nullable=false)
    private String timestamp;

    @ManyToOne
    private Course course;

    public Material(){
    }

    public Material(String filename, String timestamp, Course course){
        this.filename = filename;
        this.timestamp = timestamp;
        this.course = course;
    }

    public Material(MaterialDTO materialDTO){
        this.id = materialDTO.getId();
        this.filename = materialDTO.getFilename();
        this.timestamp = materialDTO.getTimestamp();
        this.course = materialDTO.getCourse();
    }

    public Integer getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public CourseDTO getCourse() {
        return new CourseDTO(this.course);
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
