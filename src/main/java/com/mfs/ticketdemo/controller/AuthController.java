package com.mfs.ticketdemo.controller;

import com.mfs.ticketdemo.config.JwtUtils;
import com.mfs.ticketdemo.dao.UserDao;
import com.mfs.ticketdemo.entity.UserEntity;
import com.mfs.ticketdemo.model.req.LoginReqModel;
import com.mfs.ticketdemo.model.req.SignupReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import com.mfs.ticketdemo.model.res.JwtResModel;
import com.mfs.ticketdemo.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginReqModel loginReqModel) throws Exception {
        logger.info("Sign in");
        authenticate(loginReqModel.getEmail(), loginReqModel.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginReqModel.getEmail());
        final String token = jwtUtils.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UserEntity userEntity = userDao.findUserByEmail(loginReqModel.getEmail()).isPresent()? userDao.findUserByEmail(loginReqModel.getEmail()).get() : null;
        return ResponseEntity.ok(new JwtResModel(token,roles,userEntity.getUsername(), userEntity.getPhone()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupReqModel signupReqModel) {
        logger.info("Sign up");
        if (userDao.existsByEmail(signupReqModel.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResModel(400,false,"Error: Email is already in use!"));
        }
        return ResponseEntity.ok(userDao.createUser(signupReqModel));
    }

    private void authenticate(String email, String password) throws Exception   {
        try {
             Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
