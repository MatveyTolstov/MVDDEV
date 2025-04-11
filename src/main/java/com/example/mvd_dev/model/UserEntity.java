package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long idUser;

    @Column(name = "number")
    private String number;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id_role")
    private Role role;


    public long getRoleId() {
        return role.getIdRole();
    }

    public void setRoleId(long roleId) {
        role.setIdRole(roleId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.getRoleName()))); // возращает роль пользователя
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
