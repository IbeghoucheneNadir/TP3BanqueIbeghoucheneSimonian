/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author katzenmaul
 */
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
    
}
