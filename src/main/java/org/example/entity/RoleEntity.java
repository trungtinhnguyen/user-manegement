package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "role")
@Getter
@Setter
public class RoleEntity extends BaseEntity{

    @Column (name = "code", columnDefinition = "VARCHAR(100)")
    private String code;

    @Column (name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @ManyToMany (mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> users;
}