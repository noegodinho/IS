package dto;

import data.User;

import java.io.Serializable;

public class AdministratorDTO extends UserDTO implements Serializable{
    private static final long serialVersionUID = 2L;

    public AdministratorDTO(){
    }

    public AdministratorDTO(User administrator){
        super(administrator.getId(), administrator.getHashedPassword(), administrator.getName(), administrator.getBirth(),
              administrator.getInstEmail(), administrator.getAltEmail(), administrator.getAddress(),
                administrator.getTelephone());
    }
}
