package com.mfs.ticketdemo.controller;

import com.mfs.ticketdemo.config.JwtUtils;
import com.mfs.ticketdemo.dao.UserDao;
import com.mfs.ticketdemo.model.req.LoginReqModel;
import com.mfs.ticketdemo.model.req.SignupReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import com.mfs.ticketdemo.model.res.JwtResModel;
import com.mfs.ticketdemo.service.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginReqModel loginReqModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReqModel.getEmail(), loginReqModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResModel(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupReqModel signupReqModel) {

        if (userDao.existsByEmail(signupReqModel.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResModel(400,false,"Error: Email is already in use!"));
        }
        return ResponseEntity.ok(userDao.createUser(signupReqModel));
    }

}
