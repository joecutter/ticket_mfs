package com.mfs.ticketdemo.controller;

import com.mfs.ticketdemo.dao.EventsDao;
import com.mfs.ticketdemo.dao.UserDao;
import com.mfs.ticketdemo.entity.EventsEntity;
import com.mfs.ticketdemo.model.res.ApiResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping("/detail")
    public ApiResModel getUserDetails(@RequestParam String email){
        return new ApiResModel(200, true, userDao.findUserByEmail(email));
    }
}
