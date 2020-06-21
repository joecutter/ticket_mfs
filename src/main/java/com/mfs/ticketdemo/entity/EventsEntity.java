package com.mfs.ticketdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document( collection = "Events")
public class EventsEntity implements Serializable {
    @Id
    String eventId;
    String title;
    String description;
    String fee;
    String image;
    Date createdAt;
    Date updatedAt;
}
