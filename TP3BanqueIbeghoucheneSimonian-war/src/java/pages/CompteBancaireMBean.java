/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.Bootstrap;
import session.CompteBancaireFacade;

@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private double montant;
    private List<CompteBancaire> allCompteBancaire = new ArrayList<>();

    @EJB
    private Bootstrap bootstrap;

    @EJB
    private CompteBancaireFacade compteBancaireFacade;

    public CompteBancaireMBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public void deposer(){
        compteBancaireFacade.deposer(id,montant);
        resetCache();
    }
    
    public void delete(long id){
        compteBancaireFacade.delete(id);
    }

}
