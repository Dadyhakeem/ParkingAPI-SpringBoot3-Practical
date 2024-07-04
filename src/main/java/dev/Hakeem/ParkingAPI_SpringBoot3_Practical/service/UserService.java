package dev.Hakeem.ParkingAPI_SpringBoot3_Practical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public User editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
    if (!novaSenha.equals(confirmaSenha)) {
        throw new RuntimeException("Nova senha nao confero com confirmacao de senha.");
    }
    User user = buscarPorId(id);

     if (!user.getPassword().equals(senhaAtual)) {
        throw new RuntimeException("Nova senha nao confere.");
     }
    user.setPassword(novaSenha);
    return user;
}


@Transactional(readOnly = true )
public List<User> findAll() {
    return userRepository.findAll();
    
}








 
}
