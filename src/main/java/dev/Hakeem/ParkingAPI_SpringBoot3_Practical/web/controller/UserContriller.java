package dev.hakeem.parkingapi_springboot3_practical.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hakeem.parkingapi_springboot3_practical.entities.User;
import dev.hakeem.parkingapi_springboot3_practical.service.UserService;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserCreateDto;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserResponseDTO;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserSenhaDto;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.mapper.UserMapper;
import dev.hakeem.parkingapi_springboot3_practical.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Tag(name = "User", description = "Contém todos as operacoes relativas aos recurcos pra cadastro,edicao e leitura de um usuario.")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserContriller {
     
     private final UserService userService;
    
     @Operation( summary = "Criar um novo usuario",description = "Recurso para criar um novo usuario"
               ,responses = {
                   @ApiResponse(responseCode ="201",description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponseDTO.class))),
                   @ApiResponse(responseCode = "409",description = "Usuário e-mail já cadastrado    no  sistema",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                   @ApiResponse(responseCode = "422",description = "Recurso nao processada por dados de entrada invalida",
                   content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
                }
            )    

     
    @PostMapping
    public ResponseEntity<UserResponseDTO>create(@Valid @RequestBody UserCreateDto userCreateDto){
         User obj = userService.salvar(UserMapper.toUser(userCreateDto));
         return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(obj));
         
    }


    @Operation( summary = "Recuperar um usuario pelo id",description = "Recuperar usuario pelo id "
               ,responses = {
                   @ApiResponse(responseCode ="200",description = "Recurso recuperado com sucesso",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = UserResponseDTO.class))),                  
                   @ApiResponse(responseCode = "404",description = "Recurso nao encontrado ",
                   content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
                }
            ) 

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENT')AND #id == authentication.principal.id)")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id){
        User obj = userService.buscarPorId(id);
        return ResponseEntity.ok(UserMapper.toDto(obj));
    }



 @Operation( summary = "Atualizar senha",description = "Atualizar senha"
    ,responses = {
        @ApiResponse(responseCode ="204",description = "Senha atualizada com sucesso",
         content = @Content(mediaType = "application/json",schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode = "400",description = "Senha nao confere",
         content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
         @ApiResponse(responseCode = "404",description = "Recurso nao encontrado ",
         content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),
         @ApiResponse(responseCode = "422",description = "Campos  invalida ou mal formatados",
                 content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
     }
 )


        @PatchMapping(value = "/{id}")
        public ResponseEntity<Void> updatePassword(@PathVariable Long id,@Valid @RequestBody UserSenhaDto userSenhaDto){
        User obj = userService.editarSenha(id,userSenhaDto.getSenhaAtual(),userSenhaDto.getNovaSenha(),userSenhaDto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    

    @Operation(summary = "Listar todos os usuarios", description = "Listar todos os usuarios cadastrados",
             responses = {
                         @ApiResponse(responseCode = "200",description = "Lista com todos os usuarios contratados",
                               content = @Content(mediaType = "application/json",
                               array = @ArraySchema(schema = @Schema(implementation = UserResponseDTO.class))))
             })

    @GetMapping
   public ResponseEntity<List<UserResponseDTO>> findAll( User user){
    List<User> list = userService.findAll();
    return ResponseEntity.ok(UserMapper.toListDto(list));
   }
}
