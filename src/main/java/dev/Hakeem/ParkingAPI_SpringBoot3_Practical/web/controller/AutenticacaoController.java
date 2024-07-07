package dev.hakeem.parkingapi_springboot3_practical.web.controller;


import dev.hakeem.parkingapi_springboot3_practical.jwt.JwtToken;
import dev.hakeem.parkingapi_springboot3_practical.jwt.JwtUserDetailsService;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserCreateDto;
import dev.hakeem.parkingapi_springboot3_practical.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {

    private  final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UserCreateDto userCreateDto, HttpServletRequest request){
        log.info("Processo de autenticacao pelo login {}",userCreateDto.getUsername());
           try{
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
               new UsernamePasswordAuthenticationToken(userCreateDto.getUsername(),userCreateDto.getPassword());
               authenticationManager.authenticate(usernamePasswordAuthenticationToken);
               JwtToken token = jwtUserDetailsService.getTokenAuthenticated(userCreateDto.getUsername());
               return ResponseEntity.ok(token);
           }catch (AuthenticationException ex){
               log.warn("Bad credential from username '{}'",userCreateDto.getUsername());
           }
           return  ResponseEntity
                   .badRequest()
                   .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Invalidas"));
    }






}
