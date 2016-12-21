package generated;

import java.io.Serializable;
import java.util.List;


public class Course implements Serializable{
    private static final long serialVersionUID = 5L;

    private Integer id;
    private String courseName;

    private Professor professor;

    private List<Student> students;

    private List<Material> materials;

    public Course(){
    }

    public Course(String courseName, Professor professor, List<Student> students){
        this.courseName = courseName;
        this.professor = professor;
        this.students = students;
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
