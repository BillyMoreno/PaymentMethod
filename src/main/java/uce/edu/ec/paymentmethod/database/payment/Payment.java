package uce.edu.ec.paymentmethod.database.payment;

import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.database.credit.Credit;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_id", nullable = false)
    private Credit credit;

    private double fee;

    @Column(name = "paymente_type")
    private String paymentType;

    public Payment(){

    }

    public Payment(Credit credit, double fee) {
        this.credit = credit;
        this.fee = fee;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}