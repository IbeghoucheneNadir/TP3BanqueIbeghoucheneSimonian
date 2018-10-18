/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author katzenmaul
 */
@Startup
@Singleton
@LocalBean
public class Bootstrap {

    @PersistenceContext(unitName = "TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @PostConstruct
    public void init() {
        System.out.println("orio "+em.getProperties());
        em.persist(new CompteBancaire("John Lennon", 150000));
        em.flush();
        creerComptesTest();
    }
    
    public void creerCompte(CompteBancaire cb) {
        em.persist(cb);
    }
    
    public void creerComptesTest() {  
       creerCompte(new CompteBancaire("John Lennon", 150000));  
       creerCompte(new CompteBancaire("Paul McCartney", 950000));  
       creerCompte(new CompteBancaire("Ringo Starr", 20000));  
       creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}