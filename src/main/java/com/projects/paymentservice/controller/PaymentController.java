package com.projects.paymentservice.controller;


import com.projects.paymentservice.DTO.PaymentRequestDto;
import com.projects.paymentservice.Service.PaymentService;
import com.projects.paymentservice.Service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/")
    public String createPaymentLink(@RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
        return paymentService.createPaymentLink(paymentRequestDto.getOrderId(),10000L);
    }
}
