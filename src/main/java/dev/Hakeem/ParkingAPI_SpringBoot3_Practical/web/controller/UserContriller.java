package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/users")
public class UserContriller {
     @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserResponseDTO>create(@RequestBody UserCreateDto userCreateDto){
         User obj = userService.salvar(UserMapper.toUser(userCreateDto));
         return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(obj));
         
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        User obj = userService.buscarPorId(id);
        return ResponseEntity.ok(UserMapper.toDto(obj));
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id,@RequestBody User user){
        User obj = userService.editarSenha(id,user.getPassword());
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
   public ResponseEntity<List<User>> findAll( User user){
    List<User> list = userService.findAll();
    return ResponseEntity.ok(list);
   }
}
