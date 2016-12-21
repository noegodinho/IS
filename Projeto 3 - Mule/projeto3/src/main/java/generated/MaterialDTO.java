package generated;

import java.io.Serializable;

public class MaterialDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String filename;
    private String timestamp;
    private Integer courseID;

    public MaterialDTO(){

    }

    public MaterialDTO(Material material){
        this.id = material.getId();
        this.filename = material.getFilename();
        this.timestamp = material.getTimestamp();
        this.courseID = material.getCourse().getId();
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCourseID() {
        return courseID;
    }
    
    public String toString(){
    	return this.getFilename();
    }
}
