package dev.hakeem.parkingapi_springboot3_practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hakeem.parkingapi_springboot3_practical.entities.User;



public interface UserRepository extends JpaRepository<User,Long> {

    

    
}
