package com.mfs.ticketdemo.model.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class SignupReqModel implements Serializable {
    @NotNull
    @Email(message = "Email must should be valid")
    String email;
    @NotNull( message = "Username must not be greater than 10 characters")
    String username;
    @NotNull (message = "Phone must should be valid")
    String phone;
    @NotNull
    @Min(value = 6, message = "Password must be greater than 6 characters")
    String password;
    Set<String> roles;
}
