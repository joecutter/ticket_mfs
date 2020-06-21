package com.mfs.ticketdemo.dao;

import com.mfs.ticketdemo.entity.UserEntity;
import com.mfs.ticketdemo.model.req.SignupReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    ApiResModel createUser(SignupReqModel signupReqModel);
    Optional<UserEntity> findUserByEmail(String email);
    boolean existsByEmail(String email);
    String disableUser(String phone);
    List<UserEntity> findAllUsers();
}
