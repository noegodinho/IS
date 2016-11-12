package dto;

import data.Administrator;

import java.io.Serializable;
import java.util.Date;

public class AdministratorDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    private Integer id;
    private String hashedPassword;
    private String name;
    private Date birth;
    private String instEmail;
    private String altEmail;
    private String address;
    private Integer telephone;

    public AdministratorDTO(){
    }

    public AdministratorDTO(Administrator administrator){
        this.id = administrator.getId();
        this.hashedPassword = administrator.getHashedPassword();
        this.name = administrator.getName();
        this.birth = administrator.getBirth();
        this.instEmail = administrator.getInstEmail();
        this.altEmail = administrator.getAltEmail();
        this.address = administrator.getAddress();
        this.telephone = administrator.getTelephone();
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
