package com.mfs.ticketdemo.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupReqModel {

    @NotBlank
    @Email(message = "Email should be valid")
    String email;

    @NotBlank
    @Min(value = 6, message = "Password must be greater than 6 characters")
    String password;
    Set<String> roles;
}
