package com.mfs.ticketdemo.repo;

import com.mfs.ticketdemo.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepo extends MongoRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findByPhone(String phone);
}
