package dev.hakeem.parkingapi_springboot3_practical.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;

public class JwtUserDetails extends User {

    private dev.hakeem.parkingapi_springboot3_practical.entities.User user;

    public JwtUserDetails(dev.hakeem.parkingapi_springboot3_practical.entities.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }




    public  Long getId(){
        return this.user.getId();
    }

    public  String getRole(){
        return user.getRole().name();
    }
}
