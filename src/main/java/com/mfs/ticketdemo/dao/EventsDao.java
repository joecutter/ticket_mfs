package com.mfs.ticketdemo.dao;

import com.mfs.ticketdemo.entity.EventsEntity;
import com.mfs.ticketdemo.model.res.ApiResModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventsDao {
    ApiResModel createEvent(EventsEntity eventsEntity, MultipartFile file) throws IOException;
    ApiResModel updateEvent(EventsEntity eventsEntity);
    ApiResModel findEvent(String eventId);
    ApiResModel findEvents();
    ApiResModel deleteEvent(String eventId);
}
