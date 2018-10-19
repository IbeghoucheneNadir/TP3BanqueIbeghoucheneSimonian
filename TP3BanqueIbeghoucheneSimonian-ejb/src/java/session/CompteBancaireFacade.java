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
    private final EntityManager em;

    public CompteBancaireFacade() {
        super(CompteBancaire.class);
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("TP3BanqueIbeghoucheneSimonian-ejbPU");
        em = emf.createEntityManager();
        System.out.println("Facade Cree!");
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public final List<CompteBancaire> getAllCompteBancaire() {
        if (em == null) {
            System.err.println("EntityManager is null");
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public CompteBancaire getCompteBancaire(long id) {
        if (em == null) {
            System.err.println("EntityManager is null");
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findById");
        query.setParameter("id", id);
        return (CompteBancaire) query.getSingleResult();
    }

    public final List<CompteBancaire> getRangeCompteBancaire(int start, int range) {
        if (em == null) {
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

    public CompteBancaire getCompteBancaire(int idCompteBancaire) {
        return em.find(CompteBancaire.class, idCompteBancaire);

    }
}
