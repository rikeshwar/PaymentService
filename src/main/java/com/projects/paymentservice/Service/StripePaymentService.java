package com.projects.paymentservice.Service;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter

public class StripePaymentService implements PaymentService{

    @Value("${stripe.secretKey}")
    private String stripeKey;

    @Override
    public String createPaymentLink(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = stripeKey;
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName("Sample Product")
                .build();


        Product product = Product.create(productParams);
        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(priceCreateParams);
        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
