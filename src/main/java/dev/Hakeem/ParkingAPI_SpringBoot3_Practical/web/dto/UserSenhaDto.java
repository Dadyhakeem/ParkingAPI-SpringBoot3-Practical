package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @ToString
public class UserSenhaDto {


    @NotBlank
    @Size(min = 6,max = 6)
    private String senhaAtual;

    @Size(min = 6,max = 6)
    private String novaSenha;

    @Size(min = 6,max = 6)
    private String confirmaSenha;
}
