package com.mfs.ticketdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Document( collection = "Users")
public class UserEntity implements Serializable {

    @Id
    private String id;

    @NotNull
    @Email(message = "Email should be valid")
    String email;

    @NotNull
    @Min(value = 6, message = "Password must be greater than 6 characters")
    String password;

    @DBRef
    private Set<RoleEntity> roles = new HashSet<>();
    Date createdAt;
}
