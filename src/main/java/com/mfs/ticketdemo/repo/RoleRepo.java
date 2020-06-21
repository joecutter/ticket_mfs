package com.mfs.ticketdemo.repo;

import com.mfs.ticketdemo.entity.ERole;
import com.mfs.ticketdemo.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends MongoRepository<RoleEntity, String> {
    Optional<RoleEntity> findByName(ERole name);
}