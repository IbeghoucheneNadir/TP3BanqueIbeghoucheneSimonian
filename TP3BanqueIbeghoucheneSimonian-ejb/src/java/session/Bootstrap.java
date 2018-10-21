package session;

import entities.Client;
import entities.CompteBancaire;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Startup
@Singleton
@LocalBean
public class Bootstrap {

    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("TP3BanqueIbeghoucheneSimonian-ejbPU");
        em = emf.createEntityManager();
        creerComptesTest();
        em.flush();
        creerClientsTest();
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
    
    public void creerClientsTest(){
        for(int i = 1 ; i< 11 ; i++){
            Client cli = new Client();
            cli.setAdresse("adresse" + i);
            cli.setNom("client " + i);
            cli.setPrenom("cli " + i);
            cli.setTelephone("Tel "+ i);
            Query query = em.createNamedQuery("CompteBancaire.findById");
            CompteBancaire cb,cb1,cb2;
            try{
                query.setParameter("id", i);
                cb = (CompteBancaire) query.getSingleResult();
                query.setParameter("id", i+1);
                cb1 = (CompteBancaire) query.getSingleResult();
                query.setParameter("id", i+2);
                cb2 = (CompteBancaire) query.getSingleResult();
            }catch(NoResultException e){
                System.out.println("compte bancaire " + i + " n'existe pas");
                return;
            }
          cli.addCompteBancaire(cb);
          cli.addCompteBancaire(cb1);
          cli.addCompteBancaire(cb2);
          cli.deleteCompteBancaire(cb);
          System.out.println(cli);
          em.persist(cli);
        } 
        
        for(int i = 1; i<12; i++){
            //CompteBancaire cb = em.find(CompteBancaire.class, i);
            //cb.addClient(em.find(Client.class))
          
        }
        
    }
}