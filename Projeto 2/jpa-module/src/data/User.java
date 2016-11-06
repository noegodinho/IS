package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
    protected String name;

    @Temporal(TemporalType.DATE)
    protected Date birth;
    protected String instEmail;
    protected String password;
    protected String altEmail;
    protected String address;
    protected int telephone;

    public User() {
        super();
    }

    public User(String name, Date birth, String instEmail, String password, String altEmail , String address, int telephone) {
        super();
        this.name = name;
        this.birth = birth;
        this.instEmail = instEmail;
        this.password = password;
        this.altEmail = altEmail;
        this.address = address;
        this.telephone = telephone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
