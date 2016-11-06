package data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Material implements Serializable{
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filename;
    @ManyToOne
    private Course course;
    private String url;

    public Material(){
        super();
    }

    public Material(String filename, Course course, String url){
        super();
        this.filename = filename;
        this.course = course;
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public Course getCourse() {
        return course;
    }

    public String getUrl() {
        return url;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
