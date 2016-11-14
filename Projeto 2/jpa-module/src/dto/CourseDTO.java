package dto;

import data.Course;
import data.Material;
import data.Professor;
import data.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String courseName;
    private ProfessorDTO professor;
    private List<StudentDTO> students;
    private List<MaterialDTO> materials;

    public CourseDTO(){

    }

    public CourseDTO(Course course){
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.professor = course.getProfessor();
        this.students = course.getStudents();
        this.materials = course.getMaterials();
    }

    public Integer getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Professor getProfessor() {
        return new Professor(this.professor);
    }

    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        for(StudentDTO studentDTO : this.students){
            studentList.add(new Student(studentDTO));
        }

        return studentList;
    }

    public List<Material> getMaterials() {
        List<Material> materialList = new ArrayList<>();

        for(MaterialDTO materialDTO : this.materials){
            materialList.add(new Material(materialDTO));
        }

        return materialList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProfessor(ProfessorDTO professor) {
        this.professor = professor;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public void setMaterials(List<MaterialDTO> materials) {
        this.materials = materials;
    }
}
