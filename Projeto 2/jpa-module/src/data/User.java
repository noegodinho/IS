package data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;
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
    protected int telephone;
    @Column(nullable=false)
    protected int userType;

    public User(){
        super();
    }

    public User(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                String address, int telephone, int userType){
        super();
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.birth = birth;
        this.instEmail = instEmail;
        this.altEmail = altEmail;
        this.address = address;
        this.telephone = telephone;
        this.userType = userType;
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

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
