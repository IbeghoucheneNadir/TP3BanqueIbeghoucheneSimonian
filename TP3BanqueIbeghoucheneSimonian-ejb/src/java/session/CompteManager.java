/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author katzenmaul
 */
@Named(value = "compteManager")
@Stateless
@LocalBean
public class CompteManager {

    public CompteManager() {
    }

    public CompteManager(EntityManager em) {
        this.em = em;
    }
    
    @PersistenceContext(unitName = "TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public void creerCompte(CompteBancaire cb) {
        em.persist(cb);
    }
    
    public final List<CompteBancaire> getAllCompteBancaire(){
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }
}
