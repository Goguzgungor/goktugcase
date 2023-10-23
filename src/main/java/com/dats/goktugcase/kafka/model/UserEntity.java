package com.dats.goktugcase.kafka.model;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "templateuser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity implements Serializable {


    @Column(name = "name")
private String name;


    @Column(name = "password")
private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;


    @JsonGetter("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
