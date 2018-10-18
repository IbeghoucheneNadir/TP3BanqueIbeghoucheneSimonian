/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author katzenmaul
 */
@Stateless
public class CompteBancaireFacade extends AbstractFacade<CompteBancaire> {

    @PersistenceContext(unitName = "TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManager em;
    
    @PersistenceUnit(unitName="TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManagerFactory emf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteBancaireFacade() {
        super(CompteBancaire.class);
    }
    
    public final List<CompteBancaire> getAllCompteBancaire(){
        emf = Persistence.createEntityManagerFactory("TP3BanqueIbeghoucheneSimonian-ejbPU");
        em = emf.createEntityManager();
        if(em == null){System.out.println("DAAAMMM NOOOOOO");return null;}
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
    
}
