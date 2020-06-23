package com.mfs.ticketdemo.service;

import com.mfs.ticketdemo.entity.RoleEntity;
import com.mfs.ticketdemo.entity.UserEntity;
import com.mfs.ticketdemo.repo.RoleRepo;
import com.mfs.ticketdemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepo.findByEmail(email);
        if(userEntity.isPresent()){
            List<GrantedAuthority> authorities = getUserAuthority(userEntity.get().getRoles());
            return buildUserForAuthentication(userEntity.get(), authorities);
        }else {
            throw  new UsernameNotFoundException("User Not Found with email: " + email);
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<RoleEntity> userRoles) {
        List<GrantedAuthority> authorities = userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return authorities;
    }

    private UserDetails buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }





}