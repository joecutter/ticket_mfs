package com.mfs.ticketdemo.controller;

import com.mfs.ticketdemo.dao.EventsDao;
import com.mfs.ticketdemo.entity.EventsEntity;
import com.mfs.ticketdemo.model.res.ApiResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EventsDao eventsDao;

    @PostMapping("/create")
    public ApiResModel createEvent(@RequestBody EventsEntity eventsEntity){
        return eventsDao.createEvent(eventsEntity);
    }

    @PostMapping("/update")
    public ApiResModel updateEvent(@RequestBody EventsEntity eventsEntity){
        return eventsDao.updateEvent(eventsEntity);
    }

    @GetMapping("/getEvent")
    public ApiResModel findEvent(@RequestParam String eventId){
        return eventsDao.findEvent(eventId);
    }

    @GetMapping("/getEvents")
    public ApiResModel findEvents(){
        return eventsDao.findEvents();
    }

    @DeleteMapping("/delete")
    public ApiResModel deleteEvent(@RequestParam String eventId){
        return eventsDao.deleteEvent(eventId);
    }
}
