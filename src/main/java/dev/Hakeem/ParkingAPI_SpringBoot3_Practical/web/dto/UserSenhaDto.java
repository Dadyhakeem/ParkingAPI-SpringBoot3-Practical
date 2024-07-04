package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto;

import lombok.*;

@Getter @Setter 
@NoArgsConstructor @AllArgsConstructor @ToString
public class UserSenhaDto {
    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;
}
