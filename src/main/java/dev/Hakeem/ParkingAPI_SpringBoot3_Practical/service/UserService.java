package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.entities.User;
import dev.Hakeem.ParkingAPI_SpringBoot3_Practical.repository.UserRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;
@Transactional
public User salvar(User user) {
    return userRepository.save(user);
}

@Transactional(readOnly = true)
public User buscarPorId(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
}

@Transactional
public User editarSenha(Long id,String password) {
    User user = buscarPorId(id);
    user.setPassword(password);
    return user;
}
 
}
