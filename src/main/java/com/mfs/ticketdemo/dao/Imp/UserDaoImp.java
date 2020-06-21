package com.mfs.ticketdemo.dao.Imp;

import com.mfs.ticketdemo.dao.UserDao;
import com.mfs.ticketdemo.entity.ERole;
import com.mfs.ticketdemo.entity.RoleEntity;
import com.mfs.ticketdemo.entity.UserEntity;
import com.mfs.ticketdemo.model.req.SignupReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import com.mfs.ticketdemo.repo.RoleRepo;
import com.mfs.ticketdemo.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDaoImp implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepo usersRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public ApiResModel createUser(SignupReqModel authReqModel) {
        logger.info("Creating user \n"+authReqModel.toString());
        String msg = "User Created Successfully";
        try{
            UserEntity user = new UserEntity();
            user.setEmail(authReqModel.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(authReqModel.getPassword()));

            Set<String> strRoles = authReqModel.getRoles();
            Set<RoleEntity> roles = new HashSet<>();

            if (strRoles == null) {
                RoleEntity userRole = roleRepo.findByName(ERole.ROLE_USER).get();
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            RoleEntity adminRole = roleRepo.findByName(ERole.ROLE_ADMIN).get();
                            roles.add(adminRole);

                            break;
                        case "mod":
                            RoleEntity modRole = roleRepo.findByName(ERole.ROLE_MODERATOR).get();
                            roles.add(modRole);

                            break;
                        default:
                            RoleEntity userRole = roleRepo.findByName(ERole.ROLE_USER).get();
                            roles.add(userRole);
                    }
                });
            }

            user.setRoles(roles);
            usersRepo.save(user);
            logger.info("\n====== DONE CREATING USER ======\n"+user.toString());
            return new ApiResModel(200, true, msg);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400, false, "Fail to Create User");
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepo.existsByEmail(email);
    }

    @Override
    public String disableUser(String phone) {
        return null;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return usersRepo.findAll();
    }
}
