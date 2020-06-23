package com.mfs.ticketdemo.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentReqModel {
     String amount;
     String eventId;
     String title;
     String phone;
     String app;

     public PaymentReqModel() {
          this.app = "TEST";
     }

}
