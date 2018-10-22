/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import entities.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManager em;

    public ClientFacade() {
        super(Client.class);
        updateEM();
        System.out.println("Client Facade Cree!");
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    private boolean updateEM(){
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("TP3BanqueIbeghoucheneSimonian-ejbPU");
        em = emf.createEntityManager();
        if(em == null){
            System.err.println("EntityManager is null");
            return true;
        }else{
            return false;
        }   
    }
    
    public final List<Client> getAllClient() {
        if (updateEM()) {
            return null;
        }
        Query query = em.createNamedQuery("Client.findAll");
        return query.getResultList();
    }
    
    public Client getClient(long id){
        if (updateEM()) {
            return null;
        }
        Query query = em.createNamedQuery("Client.findById");
        query.setParameter("id", id);
        Client c;
        try{
            c = (Client) query.getSingleResult();
            return c;
        }catch(NoResultException e){
            System.out.println("client " + id + " n'existe pas");
            return null;
        }
    }
    
    public boolean delete(long id) {
        Client c = getClient(id);
        if(c == null){return false;}
        
        try{
            em.remove(c);
        }catch(IllegalArgumentException | TransactionRequiredException e){
            System.out.println("client delte exception raised");
            return false;
        }
        return true;
    }
    
}
