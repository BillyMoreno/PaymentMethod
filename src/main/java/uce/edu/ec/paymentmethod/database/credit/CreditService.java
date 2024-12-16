package uce.edu.ec.paymentmethod.database.credit;

import jakarta.persistence.*;

public class CreditService {

    private EntityManager em;

    public CreditService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paymentDb");
        this.em = emf.createEntityManager();
    }
    // Create Credit
    public void createCredit(Credit credit) {
        try {
            em.getTransaction().begin();
            em.persist(credit);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Get Credit by ID
    public Credit getCredit(Long id) {
        return em.find(Credit.class, id);
    }

    // Delete Credit
    public void deleteCredit(Long id) {
        try {
            Credit credit = em.find(Credit.class, id);
            if (credit != null) {
                em.getTransaction().begin();
                em.remove(credit);
                em.getTransaction().commit();
            } else {
                System.out.println("Credit not found with id: " + id);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Update Credit
    public void updateCredit(Credit credit) {
        try {
            em.getTransaction().begin();
            em.merge(credit);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
