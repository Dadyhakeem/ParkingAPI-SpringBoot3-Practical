package dev.hakeem.parkingapi_springboot3_practical.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hakeem.parkingapi_springboot3_practical.entities.User;
import dev.hakeem.parkingapi_springboot3_practical.repository.UserRepository;
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
