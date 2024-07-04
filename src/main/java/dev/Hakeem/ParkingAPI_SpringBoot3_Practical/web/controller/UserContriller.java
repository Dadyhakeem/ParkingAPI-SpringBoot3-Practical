package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.entities.User;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.service.UserService;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.UserCreateDto;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.UserResponseDTO;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.UserSenhaDto;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/users")
public class UserContriller {
     @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserResponseDTO>create(@Valid @RequestBody UserCreateDto userCreateDto){
         User obj = userService.salvar(UserMapper.toUser(userCreateDto));
         return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(obj));
         
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        User obj = userService.buscarPorId(id);
        return ResponseEntity.ok(UserMapper.toDto(obj));
    }


        @PatchMapping(value = "/{id}")
        public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UserSenhaDto userSenhaDto){
        User obj = userService.editarSenha(id,userSenhaDto.getSenhaAtual(),userSenhaDto.getNovaSenha(),userSenhaDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    

    @GetMapping
   public ResponseEntity<List<UserResponseDTO>> findAll( User user){
    List<User> list = userService.findAll();
    return ResponseEntity.ok(UserMapper.toListDto(list));
   }
}
