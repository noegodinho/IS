package data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Administrator extends User implements Serializable{
    private static final long serialVersionUID = 2L;

    public Administrator(){
        super();
    }

    public Administrator(String hashedPassword, String name, Date birth, String instEmail, String altEmail,
                         String address, int telephone, int userType){
        super(hashedPassword, name, birth, instEmail, altEmail, address, telephone, userType);
    }
}
