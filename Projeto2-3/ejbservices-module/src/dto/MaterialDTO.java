package dto;

import data.Material;

import java.io.Serializable;

public class MaterialDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String filename;
    private String timestamp;

    public MaterialDTO(){

    }

    public MaterialDTO(Material material){
        this.id = material.getId();
        this.filename = material.getFilename();
        this.timestamp = material.getTimestamp();
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
}
