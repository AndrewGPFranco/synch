package com.drew.synch.entities;

import com.drew.synch.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 30, message = "O nome deve ter no máximo 30 caracteres")
    private String name;

    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "O e-mail não pode estar em branco")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    private String email;

    @NotBlank(message = "O nome completo não pode estar em branco")
    @Size(max = 255, message = "O nome completo deve ter no máximo 255 caracteres")
    private String fullname;

    @NotBlank(message = "O apelido não pode estar em branco")
    @Size(max = 25, message = "O apelido deve ter no máximo 25 caracteres")
    private String nickname;

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
    private String password;

    @Size(max = 255, message = "O caminho da imagem deve ter no máximo 255 caracteres")
    private String pathImage;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @NotNull(message = "A lista de (roles) não pode ser nula")
    @Size(min = 1, message = "É necessário ao menos uma (role) para o usuário")
    private Set<RoleType> roles;

    private LocalDateTime updateDate;

    @NotNull
    private LocalDateTime registrationDate;

    public User() { /* constructor is empty */ }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "idExpense=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", fullname='" + fullname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", pathImage='" + pathImage + '\'' +
                ", birthDate=" + birthDate +
                ", roles=" + roles +
                ", updateDate=" + updateDate +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
