package uce.edu.ec.paymentmethod.database.client;

import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.database.credit.Credit;

import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Cambié de AUTO a IDENTITY si estás usando una base de datos que genera IDs automáticamente
    @Column(name = "client_id")
    private Long id;
    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit> credit;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}