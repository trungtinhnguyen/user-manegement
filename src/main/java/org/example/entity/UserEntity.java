package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column (name = "username")
    private String username;

    @Column (name = "password", columnDefinition = "TEXT")
    private String password;

    @Column (name = "full_name", columnDefinition = "VARCHAR(50)")
    private String fullName;

    @Column (name = "email", columnDefinition = "VARCHAR(100)")
    private String email;

    @Column (name = "status")
    private int status;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable (name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn (name = "role_id"))
    private List<RoleEntity> roles;
}
