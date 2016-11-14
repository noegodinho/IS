package data;

import dto.CourseDTO;
import dto.MaterialDTO;
import dto.ProfessorDTO;
import dto.StudentDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    public ProfessorDTO getProfessor() {
        return new ProfessorDTO(this.professor);
    }

    public List<StudentDTO> getStudents() {
        List<StudentDTO> studentList = new ArrayList<>();

        for(Student student : this.students){
            studentList.add(new StudentDTO(student));
        }

        return studentList;
    }

    public List<MaterialDTO> getMaterials() {
        List<MaterialDTO> materialList = new ArrayList<>();

        for(Material material : this.materials){
            materialList.add(new MaterialDTO(material));
        }

        return materialList;
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
