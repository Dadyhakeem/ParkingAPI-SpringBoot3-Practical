package dev.Hakeem.parkingapi_springboot3_practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.Hakeem.parkingapi_springboot3_practical.entities.User;



public interface UserRepository extends JpaRepository<User,Long> {

    

    
}
