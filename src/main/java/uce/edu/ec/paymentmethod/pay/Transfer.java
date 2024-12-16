package uce.edu.ec.paymentmethod.pay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierPay("transfer")
public class Transfer implements Pay {

    @Override
    public String pay() {
        return "Transfer";
    }
}
