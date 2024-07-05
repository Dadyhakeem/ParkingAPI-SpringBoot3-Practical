package dev.Hakeem.parkingapi_springboot3_practical.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
 @Getter
 @Setter
 @NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionIUD= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "username",nullable = false,unique = true, length = 100)
    private String  Username;
    
    @Column(name = "password",nullable = false,length = 200)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false,length = 250)
    private Role role = Role.ROLE_CLIENT;
    
    @Column(name = "data_criacao")
    private LocalDateTime datacriacao;
   
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    
    @Column(name = "criado_por")
    private String criadoPor;
    
    @Column(name = "modificado_por")
    private String modificadoPor;
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }

   




}
