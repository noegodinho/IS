package dto;

import data.Course;
import data.Material;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class CourseDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    @XmlAttribute
    private Integer id;
    private String courseName;

    @XmlElementWrapper(name="materials")
    @XmlElement(name="material")
    private List<MaterialDTO> materials;

    public CourseDTO(){

    }

    public CourseDTO(Course course){
        this.id = course.getId();
        this.courseName = course.getCourseName();
    }

    public void addMaterial(MaterialDTO material) {
        if (this.materials == null)
            this.materials = new ArrayList<>();
        this.materials.add(material);
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
