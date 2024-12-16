package uce.edu.ec.paymentmethod.database.bank;

import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.database.credit.Credit;

import java.util.List;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bank_id")
    private Long id;
    @Column(name="bank_name")
    private String bankName;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit> credit;

    public Bank(){

    }

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
