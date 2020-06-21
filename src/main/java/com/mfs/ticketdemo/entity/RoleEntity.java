package com.mfs.ticketdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "roles")
public class RoleEntity implements Serializable {
    @Id
    private String id;
    private ERole name;
}
