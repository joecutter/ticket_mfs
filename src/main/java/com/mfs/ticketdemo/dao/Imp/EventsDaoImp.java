package com.mfs.ticketdemo.dao.Imp;

import com.mfs.ticketdemo.dao.EventsDao;
import com.mfs.ticketdemo.entity.EventsEntity;
import com.mfs.ticketdemo.model.res.ApiResModel;
import com.mfs.ticketdemo.repo.EventsRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class EventsDaoImp implements EventsDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventsRepo eventsRepo;



    @Override
    public ApiResModel createEvent(EventsEntity eventsEntity) {
        String msg = "Event Created";
        try{
            eventsEntity.setEventId(getRandomString());
            eventsRepo.save(eventsEntity);
            return new ApiResModel(200, true, msg);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400, false, "Event Not Created");
    }

    @Override
    public ApiResModel updateEvent(EventsEntity event) {
        String msg = "Event Updated";
        try{
            String eventId = event.getEventId();
            EventsEntity eventsEntity = eventsRepo.findByEventId(eventId).isPresent()? eventsRepo.findByEventId(eventId).get() : null;
            eventsEntity.setTitle(event.getTitle());
            eventsEntity.setDescription(event.getDescription());
            eventsEntity.setFee(event.getFee());
            eventsEntity.setImage(event.getImage());
            eventsRepo.save(eventsEntity);
            return new ApiResModel(200, true, msg);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400, false, "Event Not Updated");
    }

    @Override
    public ApiResModel findEvent(String eventId) {
        EventsEntity eventsEntity = eventsRepo.findByEventId(eventId).isPresent()? eventsRepo.findByEventId(eventId).get() : null;
        return new ApiResModel(200,true, eventsEntity);
    }

    @Override
    public ApiResModel findEvents() {
        List<EventsEntity> eventsEntities = eventsRepo.findAll();
        return new ApiResModel(200,true, eventsEntities);
    }

    @Override
    public ApiResModel deleteEvent(String eventId) {
        String msg = eventId+" Deleted Successfully";
        try{
            eventsRepo.deleteByEventId(eventId);
            return new ApiResModel(200,true, msg);
        }catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
        return new ApiResModel(400,false, eventId+" Fail to Delete");
    }

    public String getRandomString() {
        char[] possibleCharacters = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789").toCharArray();
        String randomStr = RandomStringUtils.random(20, 0, possibleCharacters.length - 1, false, false, possibleCharacters, new SecureRandom());
        return randomStr;
    }
}
