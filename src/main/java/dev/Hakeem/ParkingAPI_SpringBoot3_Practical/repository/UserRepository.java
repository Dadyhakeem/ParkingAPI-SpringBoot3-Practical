package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

    

    
}
