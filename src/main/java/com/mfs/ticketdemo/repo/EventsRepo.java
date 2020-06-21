package com.mfs.ticketdemo.repo;

import com.mfs.ticketdemo.entity.EventsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventsRepo extends MongoRepository<EventsEntity, String> {
    Optional<EventsEntity> findByEventId(String eventId);
    void deleteByEventId(String eventId);
}
