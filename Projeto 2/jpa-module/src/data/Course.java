package data;

import dto.CourseDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Course implements Serializable{
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique=true, nullable=false)
    private String courseName;

    @ManyToOne
    private Professor professor;

    @ManyToMany
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private List<Material> materials;

    public Course(){
    }

    public Course(String courseName, Professor professor, List<Student> students){
        this.courseName = courseName;
        this.professor = professor;
        this.students = students;
    }

    public Course(CourseDTO courseDTO){
        this.id = courseDTO.getId();
        this.courseName = courseDTO.getCourseName();
        this.professor = courseDTO.getProfessor();
        this.students = courseDTO.getStudents();
        this.materials = courseDTO.getMaterials();
    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }
}
