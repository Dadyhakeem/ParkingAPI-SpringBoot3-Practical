package parkingapi_springboot3_practical;

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
@SpringBootTest(classes = ParkingApiSpringBoot3PracticalApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/user/userInsert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/user/userDelete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
                  org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
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






}
