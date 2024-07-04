package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.mapper;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;


import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.entities.User;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.UserCreateDto;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.UserResponseDTO;

public class UserMapper {
    
    public static User toUser(UserCreateDto createDto){
        return new ModelMapper().map(createDto, User.class);
    }
    public static UserResponseDTO toDto(User user){
        String role = user.getRole().name().substring("ROLE_".length());
        PropertyMap<User,UserResponseDTO> props = new PropertyMap<User,UserResponseDTO>() {
            @Override
            protected void configure(){
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDTO.class);
    }

    public static List<UserResponseDTO> toListDto(List<User> users){
         return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
