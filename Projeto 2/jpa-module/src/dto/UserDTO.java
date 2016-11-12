package dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String hashedPassword;
    private String name;
    private Date birth;
    private String instEmail;
    private String altEmail;
    private String address;
    private Integer telephone;

    public UserDTO(){

    }

    public UserDTO(Integer id, String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                    String address, Integer telephone){
        this.id = id;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.birth = birth;
        this.instEmail = instEmail;
        this.altEmail = altEmail;
        this.address = address;
        this.telephone = telephone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getInstEmail() {
        return instEmail;
    }

    public void setInstEmail(String instEmail) {
        this.instEmail = instEmail;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }
}
