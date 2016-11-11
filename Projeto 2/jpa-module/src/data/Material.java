package data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Material implements Serializable{
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable=false)
    private String filename;
    @Column(unique=true, nullable=false)
    private String timestamp;

    @ManyToOne
    private Course course;

    public Material(){
        super();
    }

    public Material(String filename, String timestamp, Course course){
        super();
        this.filename = filename;
        this.timestamp = timestamp;
        this.course = course;
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
