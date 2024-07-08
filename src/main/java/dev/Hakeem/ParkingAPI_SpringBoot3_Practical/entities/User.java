package dev.hakeem.parkingapi_springboot3_practical.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
 @Setter
 @NoArgsConstructor
@Entity
 @EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionIUD= 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "username",nullable = false,unique = true, length = 100)
    private String  username;
    
    @Column(name = "password",nullable = false,length = 200)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false,length = 250)
    private Role role = Role.ROLE_CLIENT;


    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime datacriacao;
     @LastModifiedDate
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "criado_por")
    private String criadoPor;
    @LastModifiedBy
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
