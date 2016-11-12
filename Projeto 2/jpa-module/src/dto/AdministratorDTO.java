package dto;

import data.Administrator;

import java.io.Serializable;
import java.util.Date;

public class AdministratorDTO extends UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    public AdministratorDTO(){
    }

    public AdministratorDTO(Administrator administrator){
        super(administrator.getId(), administrator.getHashedPassword(), administrator.getName(), administrator.getBirth(),
              administrator.getInstEmail(), administrator.getAltEmail(), administrator.getAddress(),
                administrator.getTelephone());
    }
}
