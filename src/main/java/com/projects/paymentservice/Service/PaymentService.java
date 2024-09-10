package com.projects.paymentservice.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;

public interface PaymentService {
    String createPaymentLink(String orderId, Long amount) throws StripeException;
}
