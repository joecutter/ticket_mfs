package com.mfs.ticketdemo.dao.Imp;

import com.mfs.ticketdemo.dao.PaymentDao;
import com.mfs.ticketdemo.entity.PaymentEntity;
import com.mfs.ticketdemo.model.req.PaymentReqModel;
import com.mfs.ticketdemo.model.res.ApiResModel;
import com.mfs.ticketdemo.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentDaoImpl {

    @Autowired
    PaymentDao paymentDao;
    @Autowired
    PaymentRepo paymentRepo;

    public ApiResModel mpesaStk(PaymentReqModel paymentReqModel){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount(paymentReqModel.getAmount());
        paymentEntity.setEventId(paymentReqModel.getEventId());
        paymentEntity.setPhone(paymentReqModel.getPhone());
        paymentEntity.setTitle(paymentReqModel.getTitle());
        paymentRepo.save(paymentEntity);
        return new ApiResModel(200,true,paymentDao.stk(paymentReqModel));
    }

    public ApiResModel userBalance(String phone){
        return new ApiResModel(200,true,paymentDao.getWallet(phone));
    }

    public Optional<PaymentEntity> userPaymentLogs(String phone){
        return paymentRepo.findByPhone(phone);
    }
}
