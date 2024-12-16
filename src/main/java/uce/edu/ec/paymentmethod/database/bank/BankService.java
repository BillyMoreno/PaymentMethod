package uce.edu.ec.paymentmethod.database.bank;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import uce.edu.ec.paymentmethod.database.bank.Bank;

public class BankService {

    private EntityManager em;

    public BankService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paymentDb");
        this.em = emf.createEntityManager();
    }

    // Create Bank
    public void createBank(Bank bank) {
        try {
            em.getTransaction().begin();
            em.persist(bank);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Get Bank by ID
    public Bank getBank(Long id) {
        return em.find(Bank.class, id);
    }

    // Delete Bank
    public void deleteBank(Long id) {
        try {
            Bank bank = em.find(Bank.class, id);
            if (bank != null) {
                em.getTransaction().begin();
                em.remove(bank);
                em.getTransaction().commit();
            } else {
                System.out.println("Bank not found with id: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Update Bank
    public void updateBank(Bank bank) {
        try {
            em.getTransaction().begin();
            em.merge(bank);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Buscar banco por nombre y devolver la entidad completa
    public Bank getBankByName(String bankName) {
        try {
            TypedQuery<Bank> query = em.createQuery(
                    "SELECT b FROM Bank b WHERE b.bankName = :bankName", Bank.class);
            query.setParameter("bankName", bankName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No bank found with name: " + bankName);
            return null; // Devuelve null si no se encuentra el banco
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving bank by name");
        }
    }


}
