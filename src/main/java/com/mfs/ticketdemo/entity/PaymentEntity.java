package com.mfs.ticketdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document( collection = "payments")
@JsonIgnoreProperties
public class PaymentEntity implements Serializable {
    @Id
    String eventId;
    String amount;
    String title;
    String phone;
}
