package dev.Hakeem.parkingapi_springboot3_practical.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Hakeem.parkingapi_springboot3_practical.dto.UserCreateDto;
import dev.Hakeem.parkingapi_springboot3_practical.dto.UserResponseDTO;
import dev.Hakeem.parkingapi_springboot3_practical.dto.UserSenhaDto;
import dev.Hakeem.parkingapi_springboot3_practical.dto.mapper.UserMapper;
import dev.Hakeem.parkingapi_springboot3_practical.entities.User;
import dev.Hakeem.parkingapi_springboot3_practical.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserContriller {
     
     private final UserService userService;
    
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
