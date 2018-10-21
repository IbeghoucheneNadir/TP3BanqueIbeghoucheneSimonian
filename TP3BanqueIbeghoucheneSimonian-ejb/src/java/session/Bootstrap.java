package session;

import entities.CompteBancaire;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        creerComptesTest();
        em.flush();
    }
    
    public void creerCompte(CompteBancaire cb) {
        persist(cb);
    }
    
    public void creerComptesTest() {  
       creerCompte(new CompteBancaire("John Lennon", 150000));  
       creerCompte(new CompteBancaire("Paul McCartney", 950000));  
       creerCompte(new CompteBancaire("Ringo Starr", 20000));  
       creerCompte(new CompteBancaire("Georges Harrisson", 100000));  
       
       // On crée en tout 60 comptes bancaires
        for (int i = 0; i < 56; i++) {
        creerCompte(new CompteBancaire("Compte bancaire "+i,(int)(Math.random()*10000)));  
        }
        
    }
}