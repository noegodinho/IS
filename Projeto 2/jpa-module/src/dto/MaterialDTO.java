package dto;

public class MaterialDTO{
    private Integer id;
    private String filename;
    private String timestamp;
    private CourseDTO course;

    public MaterialDTO(){

    }

    public MaterialDTO(Integer id, String filename, String timestamp, CourseDTO course){
        this.id = id;
        this.filename = filename;
        this.timestamp = timestamp;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
