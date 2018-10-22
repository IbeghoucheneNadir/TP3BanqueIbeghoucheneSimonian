package pages;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.Bootstrap;
import session.CompteBancaireFacade;

@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id, id1, id2;
    private double montant;
    private String nom;
    private CompteBancaire compteBancaire;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    private List<CompteBancaire> allCompteBancaire = new ArrayList<>();
    private String message;

    @EJB
    private Bootstrap bootstrap;

    @EJB
    private CompteBancaireFacade compteBancaireFacade;

    public CompteBancaireMBean() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public List<CompteBancaire> getAllCompteBancaire() {
        System.out.println("getAllCompteBancaire Called!");
        if (this.allCompteBancaire.isEmpty()) {
            System.out.println("getAllCompteBancaire CACHED!");
            List<CompteBancaire> cb = compteBancaireFacade.getAllCompteBancaire();
            allCompteBancaire = cb;
        }
        return allCompteBancaire;
    }
    
        public CompteBancaire getCompteBancaire() {
       
        return compteBancaire;
    }

    public void resetCache() {
        this.allCompteBancaire = new ArrayList<>();
    }

    public void creerComptesTest() {
        bootstrap.init();
        resetCache();
    }

    public String showDetails(int compteBancaireId) {
        System.out.println("YESS");
        return "CompteBancaireDetails?IdcompteBancaire=" + compteBancaireId;
    }

    public void deposer() {
        try {
            compteBancaireFacade.deposer(id, montant);
            handleMessage(true,"Transfere effectué");
        } catch (Exception e) {
            handleMessage(false,"le compte bancaire " + id + " n'existe pas");
        }
    }
    
     public void retirer() {
        if(compteBancaireFacade.retirer(id, montant)){
            handleMessage(true,"Retrait effectué");
        }else{
            handleMessage(false,"Retrait n'est pas effectué, verifier id" + id + " ou montant");
        }
    }
      public void transferer() {
        if(compteBancaireFacade.transferer(id1, id2, montant)){   
            handleMessage(true,"Transfert effectué");
        }else{
            handleMessage(false,"Transfert non effectué");
        }
    }
      
      private void handleMessage(boolean isSuccess, String leMessage){
          resetCache();
          FacesContext context = FacesContext.getCurrentInstance();
          message = leMessage;
          if(isSuccess){
            context.addMessage(null, new FacesMessage("Successful", "Your message: " + message));
            return;
          }
          context.addMessage(null, new FacesMessage("Unsuccessful", "Your message: " + message));
      }

    public void delete(long id) {
        System.out.println("deleting...");
        if(compteBancaireFacade.delete(id)){
            handleMessage(true,"le compte bancaire " + id + " est supprime");
        }else{
            handleMessage(true,"le compte bancaire " + id + "le compte bancaire " + id + " n'existe pas");
        }
    }
    
    public void creerCompte(){
        System.out.println("Creation nouveau compte");
        if(compteBancaireFacade.creerNouveauCompte(nom,montant)){
            handleMessage(true,"le compte bancaire pour " + nom + " est cree");
        }else{
            handleMessage(false,"le compte bancaire pour " + nom + " n'est pas cree");
        }
    }
    public String showOperation(int id){
        return "OperationCompteList?id="+id+"&amp;faces-redirect=true";
    }
    
     public String updateCompte(int id){
        return "updateCompte?id="+id+"&amp;faces-redirect=true";
    }

}
