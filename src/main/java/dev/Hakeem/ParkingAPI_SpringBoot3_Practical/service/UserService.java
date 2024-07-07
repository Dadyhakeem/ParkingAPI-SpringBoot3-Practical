package dev.hakeem.parkingapi_springboot3_practical.service;

import java.util.List;

import dev.hakeem.parkingapi_springboot3_practical.entities.Role;
import dev.hakeem.parkingapi_springboot3_practical.exception.PasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hakeem.parkingapi_springboot3_practical.entities.User;
import dev.hakeem.parkingapi_springboot3_practical.exception.EntityNotFoundException;
import dev.hakeem.parkingapi_springboot3_practical.exception.UsernameUniqueViolationException;
import dev.hakeem.parkingapi_springboot3_practical.repository.UserRepository;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class UserService {
   @Autowired 
   private final UserRepository userRepository;

   @Transactional
public User salvar(User user) {
    try{ 
    return userRepository.save(user);
    } catch(DataIntegrityViolationException ex){
        throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado",user.getUsername()));
    }
}

@Transactional(readOnly = true)
public User buscarPorId(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Usuario id=%s nao encontrado", id)));
}

@Transactional
public User editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
    if (!novaSenha.equals(confirmaSenha)) {
        throw new PasswordInvalidException("Nova senha nao confero com confirmacao de senha.");
    }
    User user = buscarPorId(id);

     if (!user.getPassword().equals(senhaAtual)) {
        throw new PasswordInvalidException("Nova senha nao confere.");
     }
    user.setPassword(novaSenha);
    return user;
}


@Transactional(readOnly = true )
public List<User> findAll() {
    return userRepository.findAll();
    
}

    @Transactional(readOnly = true )
    public User uscarPorUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException(String.format("Usuario username=%s nao encontrado", username)));
    }

    public Role buscarRolePorUseername(String username) {
          return  userRepository.findRoleByUsername(username);
    }
}
