package session;

import entities.CompteBancaire;
import entities.OperationBancaire;
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
        if(em == null){
            System.err.println("EntityManager is null");
            return true;
        }else{
            return false;
        }   
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public final List<CompteBancaire> getAllCompteBancaire() {
        if (updateEM()) {
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findAll");
        return query.getResultList();
    }

    public CompteBancaire getCompteBancaire(long id){
        if (updateEM()) {
            return null;
        }
        Query query = em.createNamedQuery("CompteBancaire.findById");
        query.setParameter("id", id);
        CompteBancaire cb;
        try{
            cb = (CompteBancaire) query.getSingleResult();
            return cb;
        }catch(NoResultException e){
            System.out.println("compte bancaire " + id + " n'existe pas");
            return null;
        }
    }

    public final List<CompteBancaire> getRangeCompteBancaire(int start, int range) {
        if (updateEM()) {
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

 
    public boolean deposer(int id, double montant){
        CompteBancaire cb = getCompteBancaire(id);
        if(cb == null) {return false;}
        System.out.println("montant" +montant);
        System.out.println("name ==="   +cb.getNom());
        cb.deposer(montant);
        em.merge(cb);
        em.flush();
        System.out.println("Nouveau Solde = " + cb.getSolde());
        return true;
    }
    
    public boolean retirer(int id, double montant){
        System.out.println("montant = " +montant);
        System.out.println("name = "   +getCompteBancaire(id).getNom());
        CompteBancaire cb = getCompteBancaire(id);
        if(cb == null){return false;}
        if(cb.retirer(montant) != 0){
            em.merge(cb);
            em.flush();
            System.out.println("Nouveau Solde = " + cb.getSolde());
            return true;
        }else{
            return false;
        }
    }
    public boolean delete(long id) {
        CompteBancaire cb = getCompteBancaire(id);
        if(cb == null){return false;}
        
        try{
            em.remove(cb);
        }catch(IllegalArgumentException | TransactionRequiredException e){
            System.out.println("delte exception raised");
            return false;
        }
        return true;
    }

    public boolean transferer(int id1, int id2, double montant){
        if(retirer(id1,montant)){
            if(deposer(id2,montant)){
                return true;
            }
            System.out.println("impossible de deposer au compte " + id2);
        }
        System.out.println("impossible de retirer au compte " + id1);
        return false;
    }

    public boolean creerNouveauCompte(String nom, double montant) {
        if(!nom.isEmpty() && montant >= 0){
            CompteBancaire cb = new CompteBancaire( nom, montant);
            try{
                em.persist(cb);
                return true;
            }catch(Exception e){
                System.out.println("CompteBancaire existe deja");
            }
        }
        return false;
    }
    
    public List<OperationBancaire> getOperations(int id) {
         if (updateEM()) {
            return null;
        }
        return getCompteBancaire(id).getListOperationBancaire(id);
    }
}
