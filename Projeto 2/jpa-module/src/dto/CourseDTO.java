package dto;

import java.util.List;

public class CourseDTO{
    private Integer id;
    private String courseName;
    private ProfessorDTO professor;
    private List<StudentDTO> students;
    private List<MaterialDTO> materials;

    public CourseDTO(){

    }

    public CourseDTO(Integer id, String courseName, ProfessorDTO professor, List<StudentDTO> students){
        this.id = id;
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

    public ProfessorDTO getProfessor() {
        return professor;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public List<MaterialDTO> getMaterials() {
        return materials;
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
