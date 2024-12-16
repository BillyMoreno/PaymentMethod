package uce.edu.ec.paymentmethod.pay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPay("paypal")
public class Paypal implements Pay {

    @Override
    public String pay() {
        return "Paypal";
    }
}
