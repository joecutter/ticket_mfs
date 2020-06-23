package com.mfs.ticketdemo.dao;

import com.mfs.ticketdemo.model.req.PaymentReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "kisokopay", url="https://payment.kisoko.org/api/v1")
public interface PaymentDao {

    @PostMapping("/mpesa/stk")
    String stk(PaymentReqModel paymentModel);

    @PostMapping("/wallet/userwallet")
    ApiResModel getWallet(@RequestParam String account);

    @PostMapping("/wallet/userwalletlogs")
    ApiResModel getWalletLogs(@RequestParam String account);
}
