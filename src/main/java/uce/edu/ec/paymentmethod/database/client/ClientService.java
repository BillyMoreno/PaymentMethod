package uce.edu.ec.paymentmethod.database.client;

import jakarta.persistence.*;


public class ClientService {

    private EntityManager em;

    public ClientService() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("paymentDb");
        this.em = emf.createEntityManager();
    }

    // Create Client
    public void createClient(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    // Get Client by ID
    public Client getClient(Long id) {
        return em.find(Client.class, id);
    }

    // Update Client
    public boolean updateClient(Client client) {
        Client existingClient = em.find(Client.class, client.getId());
        if (existingClient != null) {
            em.getTransaction().begin();
            em.merge(client);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    // Delete Client
    public boolean deleteClient(Long id) {
        Client client = em.find(Client.class, id);
        if (client != null) {
            em.getTransaction().begin();
            em.remove(client);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    // Buscar cliente por nombre y devolver el cliente completo
    public Client getClientByName(String name) {
        try {
            TypedQuery<Client> query = em.createQuery(
                    "SELECT c FROM Client c WHERE c.name = :name", Client.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No client found with name: " + name);
            return null; // Devuelve null si no se encuentra el cliente
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving client by name");
        }
    }



}
