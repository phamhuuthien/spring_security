package com.SpringSecurity.SpringSecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @SequenceGenerator(
            name="roles_sequence",
            sequenceName = "roles_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "roles_sequence"
    )
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users = new HashSet<>();


    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
