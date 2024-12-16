package uce.edu.ec.paymentmethod.database.payment;

import jakarta.inject.Inject;
import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.pay.Pay;
import uce.edu.ec.paymentmethod.pay.QualifierPay;

public class PaymentService {

    private EntityManager em;

    public PaymentService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paymentDb");
        this.em = emf.createEntityManager();
    }

    @Inject
    @QualifierPay("creditCard")
    private Pay creditCard;

    @Inject
    @QualifierPay("paypal")
    private Pay paypal;

    @Inject
    @QualifierPay("transfer")
    private Pay transfer;

    // Create Payment
    public void createPayment(Payment payment, String paymentType) {
        try {
            switch (paymentType.toLowerCase()) {
                case "creditcard":
                    payment.setPaymentType(creditCard.pay());
                    break;
                case "paypal":
                    payment.setPaymentType(paypal.pay());
                    break;
                case "transfer":
                    payment.setPaymentType(transfer.pay());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
            }
            em.getTransaction().begin();
            em.persist(payment);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Get Payment by ID
    public Payment getPayment(Long id) {
        return em.find(Payment.class, id);
    }

    // Delete Payment
    public void deletePayment(Long id) {
        try {
            Payment payment = em.find(Payment.class, id);
            if (payment != null) {
                em.getTransaction().begin();
                em.remove(payment);
                em.getTransaction().commit();
            } else {
                System.out.println("Payment not found with id: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Update Payment
    public void updatePayment(Payment payment) {
        try {
            em.getTransaction().begin();
            em.merge(payment);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
