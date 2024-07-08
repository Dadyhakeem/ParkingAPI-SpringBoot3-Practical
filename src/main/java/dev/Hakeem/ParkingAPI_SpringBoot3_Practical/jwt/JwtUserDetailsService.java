package dev.hakeem.parkingapi_springboot3_practical.jwt;

import dev.hakeem.parkingapi_springboot3_practical.entities.Role;
import dev.hakeem.parkingapi_springboot3_practical.entities.User;
import dev.hakeem.parkingapi_springboot3_practical.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
       private  final UserService userService;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.uscarPorUsername(username);
        return  new JwtUserDetails(user);
    }


    public JwtToken getTokenAuthenticated(String username) {
       Role role = userService.buscarRolePorUseername(username);
        return JwtUtils.createToken(username,role.name().substring("ROLE_".length()));
    }
}
