package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Integer id;
    @Column(nullable=false)
    protected String hashedPassword;
    @Column(nullable=false)
    protected String name;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    protected Date birth;
    @Column(unique=true, nullable=false)
    protected String instEmail;
    @Column(nullable=false)
    protected String altEmail;
    @Column(nullable=false)
    protected String address;
    @Column(nullable=false)
    protected Integer telephone;

    public User(){
    }

    public User(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                String address, Integer telephone){
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.birth = birth;
        this.instEmail = instEmail;
        this.altEmail = altEmail;
        this.address = address;
        this.telephone = telephone;
    }

    public User(Integer id, String hashedPassword, String name, Date birth, String instEmail, String altEmail,
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
