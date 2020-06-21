package com.mfs.ticketdemo.model.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResModel {
    int code;
    boolean status;
    Object message;
}
