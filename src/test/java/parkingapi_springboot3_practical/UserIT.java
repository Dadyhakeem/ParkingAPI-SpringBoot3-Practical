package parkingapi_springboot3_practical;

import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserSenhaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import dev.hakeem.parkingapi_springboot3_practical.ParkingApiSpringBoot3PracticalApplication;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserCreateDto;
import dev.hakeem.parkingapi_springboot3_practical.web.dto.UserResponseDTO;
import dev.hakeem.parkingapi_springboot3_practical.web.exception.ErrorMessage;

import java.util.List;

@SpringBootTest(classes = ParkingApiSpringBoot3PracticalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:sql/user/user-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:sql/user/user-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserIT {
   
   
    @Autowired
    WebTestClient testClient;
  
    @Test
    public void createUser_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201()
    {
    UserResponseDTO responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("quetinha@gmail.com","123456"))
                  .exchange()
                  .expectStatus().isCreated()
                  .expectBody(UserResponseDTO.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("quetinha@gmail.com");
                  org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENT");
    }
    @Test
    public void createUser_ComUsernameValidos_RetornarErroMessagemStatus422()
    {
    ErrorMessage responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("","123456"))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    
             responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("ailton@","123456"))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    
             responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("ailton@email.","123456"))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    }
    @Test
    public void createUser_ComPasswordValidos_RetornarErroMessagemStatus422()
    {
    ErrorMessage responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("aiton@gamil.com",""))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    
             responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("ailton@gmail.com","12345"))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    
             responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("ailton@gmail.com","1234567"))
                  .exchange()
                  .expectStatus().isEqualTo(422)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
                  
                  
    }
    
    @Test
    public void createUser_ComUsernameRepetido_RetornarErroMessagemStatus409()
    {
    ErrorMessage responseBody = testClient
                  .post()
                  .uri("/api/v1/users")
                  .contentType(MediaType.APPLICATION_JSON)
                  .bodyValue(new UserCreateDto("dady@gmail.com","123456"))
                  .exchange()
                  .expectStatus().isEqualTo(409)
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
    }

    @Test
    public void buscarUser_ComUIdExistente_RetornarUsuarioComStatus200()
    {
    UserResponseDTO responseBody = testClient
                  .get()
                  .uri("/api/v1/users/100")                
                  .exchange()
                  .expectStatus().isOk()
                  .expectBody(UserResponseDTO.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(100);
                  org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("ana@gmail.com");
                  org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
    }
    @Test
    public void buscarUser_ComUIdInexistente_RetornarUsuarioErrorMessageComStatus404()
    {
    ErrorMessage responseBody = testClient
                  .get()
                  .uri("/api/v1/users/0")                
                  .exchange()
                  .expectStatus().isNotFound()
                  .expectBody(ErrorMessage.class)
                  .returnResult().getResponseBody();

                  org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
                  org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
                 
    }


    @Test
    public void editarSenha_ComDadosValidos_RetornarStatus204() {
        testClient
                .patch()
                .uri("/api/v1/users/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isNoContent();


    }


    @Test
    public void editarSenha_ComUIdInexistente_RetornarUsuarioErrorMessageComStatus404() {
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/v1/users/0")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("123456", "123456", "123456"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }


    @Test
    public void editarSenha_ComCamposInvalidos_RetornarUsuarioErrorMessageComStatus422() {
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/v1/users/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("", "", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        testClient
                .patch()
                .uri("/api/v1/users/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("12345", "12345", "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);


        testClient
                .patch()
                .uri("/api/v1/users/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("1234567", "1234567", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);



    }


    @Test
    public void editarSenha_ComSenhasInvalidos_RetornarUsuarioErrorMessageComStatus400() {
        ErrorMessage responseBody = testClient
                .patch()
                .uri("/api/v1/users/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("123456", "123456", "000000"))
                .exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);


        testClient
                .patch()
                .uri("/api/v1/users/101")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserSenhaDto("000000", "123456", "123456"))
                .exchange()
                .expectStatus().isEqualTo(400)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);




    }

    @Test
    public void listarUsuarios_SemqualquerParametro_RetornarlistadeUsuarioComStatus200() {
        List<UserResponseDTO> userResponseDTOS = testClient
                .get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserResponseDTO.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(userResponseDTOS).isNotNull();
        org.assertj.core.api.Assertions.assertThat(userResponseDTOS.size()).isEqualTo(4);

    }

}
