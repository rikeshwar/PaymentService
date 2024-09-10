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
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter

public class StripePaymentService implements PaymentService{



    @Override
    public String createPaymentLink(String orderId, Long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51Px82oGZMqN50czi3osEbIkSjNNbYRkF0PSmA9S6p0SfOtrX3F5xqYLKL5wEoWca0ipUMNpLBxOStyGB7fUE9Ndx00JSZg8szR";
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName("Sample Product")
                .build();


        Product product = Product.create(productParams);
        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("usd")
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
