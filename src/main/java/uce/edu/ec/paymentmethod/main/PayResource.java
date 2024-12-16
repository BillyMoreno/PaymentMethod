package uce.edu.ec.paymentmethod.main;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import uce.edu.ec.paymentmethod.database.bank.Bank;
import uce.edu.ec.paymentmethod.database.bank.BankService;
import uce.edu.ec.paymentmethod.database.client.Client;
import uce.edu.ec.paymentmethod.database.client.ClientService;
import uce.edu.ec.paymentmethod.database.credit.Credit;
import uce.edu.ec.paymentmethod.database.credit.CreditService;
import uce.edu.ec.paymentmethod.database.payment.Payment;
import uce.edu.ec.paymentmethod.database.payment.PaymentService;



@Path("/main")
public class PayResource {

    @GET
    @Produces("text/plain")
    public String instructions() {

        return "1ro crear el Cliente, 2do crear el banco, 3ro crear el credito, 4to registrar el pago, Si se quiere realizar pagos o creditos y los nombres del cliente, banco o credito no existen en la base de datos no se realizara ninguna accion";
    }

    @GET
    @Produces("text/plain")
    @Path("/createClient")
    public String createClient(@QueryParam("nameClient") String name) {
        try {
            ClientService clientService = new ClientService();
            clientService.createClient(new Client(name));
            return "Client created successfully";
        } catch (Exception e) {
            return "Error creating client: " + e.getMessage();
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/createCredit")
    public String createCredit(@QueryParam("clientName") String clientName, @QueryParam("bankName") String bankName, @QueryParam("totalCredit") double totalCredit) {
        try {
            BankService bankService = new BankService();
            ClientService clientService = new ClientService();
            CreditService creditService = new CreditService();

            Credit credit = new Credit(bankService.getBankByName(bankName), clientService.getClientByName(clientName), totalCredit);
            creditService.createCredit(credit);
            return "Credit created successfully";
        } catch (Exception e) {
            return "Error creating credit: " + e.getMessage();
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/createBank")
    public String createBank(@QueryParam("nameBank") String name) {
        try {
            BankService bankService = new BankService();
            bankService.createBank(new Bank(name));
            return "Bank created successfully";
        } catch (Exception e) {
            return "Error creating Bank: " + e.getMessage();
        }
    }

    @GET
    @Produces("text/plain")
    @Path("/createPayment")
    public String createPayment(@QueryParam("creditID") Long creditID, @QueryParam("fee") double fee, @QueryParam("paymentType") String paymentType) {
        try {
            CreditService creditService = new CreditService();
            PaymentService paymentService = new PaymentService();
            paymentService.createPayment(new Payment(creditService.getCredit(creditID), fee), paymentType);

            return "Payment register successfully";
        } catch (Exception e) {
            return "Error register payment: " + e.getMessage();
        }
    }

}


//    @GET
//    @Produces("text/plain")
//    @Path("/creditCard")
//    public String paymentCreditCard() {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paymentDb");
//        EntityManager em = emf.createEntityManager();
//        ClientService clientService = new ClientService(em);
//
//        clientService.createClient(new Client("Billy"));
//
//        return creditCard.pay(301.55);
//    }

