package dev.hakeem.parkingapi_springboot3_practical.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @ToString
public class UserResponseDTO {
    private Long id;
    private String username;
    private String role;
}
