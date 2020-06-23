package com.mfs.ticketdemo.controller;


import com.mfs.ticketdemo.dao.Imp.PaymentDaoImpl;
import com.mfs.ticketdemo.model.req.PaymentReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/payment")
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PaymentDaoImpl paymentDao;

    @PostMapping("/checkout")
    public ApiResModel checkout(@RequestBody PaymentReqModel paymentReqModel){
        return paymentDao.mpesaStk(paymentReqModel);
    }

    @GetMapping("/balance")
    public ApiResModel balance(@RequestParam String phone){
        return paymentDao.userBalance(phone);
    }

    @GetMapping("/translogs")
    public ApiResModel translogs(@RequestParam String phone){
        return new ApiResModel(200, true, paymentDao.userPaymentLogs(phone));
    }
}
