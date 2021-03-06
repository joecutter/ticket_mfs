package com.mfs.ticketdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document( collection = "events")
@JsonIgnoreProperties
public class EventsEntity implements Serializable {
    @Id
    String eventId;
    String title;
    String category;
    String description;
    String prize;
    String image;
    String venue;
    String email;
    Date createdAt;
    Date updatedAt;

}
