package com.mfs.ticketdemo.dao;

import com.mfs.ticketdemo.entity.EventsEntity;
import com.mfs.ticketdemo.model.res.ApiResModel;

import java.util.List;

public interface EventsDao {
    ApiResModel createEvent(EventsEntity eventsEntity);
    ApiResModel updateEvent(EventsEntity eventsEntity);
    ApiResModel findEvent(String eventId);
    ApiResModel findEvents();
    ApiResModel deleteEvent(String eventId);
}
