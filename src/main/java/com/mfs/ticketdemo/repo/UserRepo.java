package com.mfs.ticketdemo.repo;

import com.mfs.ticketdemo.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
