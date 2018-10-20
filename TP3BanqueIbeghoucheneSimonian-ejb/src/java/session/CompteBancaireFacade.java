package session;

import entities.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CompteBancaireFacade extends AbstractFacade<CompteBancaire> {

    @PersistenceContext(unitName = "TP3BanqueIbeghoucheneSimonian-ejbPU")
    private EntityManager em;

    public CompteBancaireFacade() {
        super(CompteBancaire.class);
        updateEM();
        System.out.println("Facade Cree!");
    }
    
    private boolean updateEM(){
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("TP3BanqueIbeghoucheneSimonian-ejbPU");
        em = emf.createEntityManager();
        return em == null;     
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public final List<CompteBancaire> getAllCompteBancaire() {
        if (updateEM()) {
            System.err.println("EntityManager is null");
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        System.out.println("getAllCompteBancaire (Facade) "+ query.getResultList());
        return query.getResultList();
    }

    public CompteBancaire getCompteBancaire(long id) {
        if (updateEM()) {
            System.err.println("EntityManager is null");
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findById");
        query.setParameter("id", id);
        return (CompteBancaire) query.getSingleResult();
    }

    public final List<CompteBancaire> getRangeCompteBancaire(int start, int range) {
        if (updateEM()) {
            System.err.println("EntityManager is null");
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        query.setFirstResult(start);
        query.setMaxResults(range);
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public void creerCompte(CompteBancaire cb) {
        em.persist(cb);
    }

    public CompteBancaire update(CompteBancaire cb) {
        return em.merge(cb);
    }

 
    public void deposer(int id, double montant) {
        if (updateEM()) {
            System.err.println("EntityManager is null");
        }
        System.out.println("montant" +montant);
        System.out.println("name ==="   +getCompteBancaire(id).getNom());
        CompteBancaire cb = getCompteBancaire(id);
        cb.deposer(montant);
        em.merge(cb);
        em.flush();
        System.out.println("Nouveau Solde = " + cb.getSolde());
    }

    public void delete(long id) {
        if (updateEM()) {
            System.err.println("EntityManager is null");
        }
        CompteBancaire cb = getCompteBancaire(id);
        em.remove(cb);
    }
    
}
