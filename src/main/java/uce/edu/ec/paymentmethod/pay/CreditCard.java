package uce.edu.ec.paymentmethod.pay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPay("creditCard")
public class CreditCard implements Pay {

    @Override
    public String pay() {
        return "CreditCard";
    }
}
