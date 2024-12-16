package uce.edu.ec.paymentmethod.database.credit;

import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.database.bank.Bank;
import uce.edu.ec.paymentmethod.database.client.Client;
import uce.edu.ec.paymentmethod.database.payment.Payment;

import java.util.List;

@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payment;

    @Column(name = "total_credit")
    private double totalCredit;

    public Credit() {
    }

    public Credit(Bank bank, Client client, double totalCredit) {
        this.bank = bank;
        this.totalCredit = totalCredit;
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}