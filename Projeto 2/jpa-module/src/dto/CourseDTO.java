package dto;

import data.Course;
import data.Material;
import data.Professor;
import data.Student;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO{
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
        this.setProfessor(course.getProfessor());
        this.setStudents(course.getStudents());
        this.setMaterials(course.getMaterials());
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

    public void setProfessor(Professor professor) {
        this.professor = new ProfessorDTO(professor);
    }

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>();

        for(Student student : students){
            this.students.add(new StudentDTO(student));
        }
    }

    public void setMaterials(List<Material> materials) {
        this.materials = new ArrayList<>();

        for(Material material : materials){
            this.materials.add(new MaterialDTO(material));
        }
    }
}
