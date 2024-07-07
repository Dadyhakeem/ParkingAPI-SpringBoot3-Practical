package dev.hakeem.parkingapi_springboot3_practical.repository;

import dev.hakeem.parkingapi_springboot3_practical.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.hakeem.parkingapi_springboot3_practical.entities.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {


   Optional<User>  findByUsername(String username);

   @Query("select u.role from Usuario u where u.username like :username")
   Role findRoleByUsername(String username);
}
