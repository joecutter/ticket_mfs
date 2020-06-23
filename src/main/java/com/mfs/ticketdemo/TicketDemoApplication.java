package com.mfs.ticketdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TicketDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketDemoApplication.class, args);
    }

}
