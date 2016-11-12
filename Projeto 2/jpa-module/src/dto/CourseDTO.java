package dto;

import data.Course;
import data.Material;
import data.Professor;
import data.Student;

import java.util.List;

public class CourseDTO{
    private Integer id;
    private String courseName;
    private Professor professor;
    private List<Student> students;
    private List<Material> materials;

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
        return professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setId(Integer id) {
        this.id = id;
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
