package com.mfs.ticketdemo.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqModel {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
