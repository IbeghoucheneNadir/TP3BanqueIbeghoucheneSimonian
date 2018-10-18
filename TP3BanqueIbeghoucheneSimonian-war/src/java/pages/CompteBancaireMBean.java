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

    @EJB
    private Bootstrap bootstrap;

    @EJB
    private CompteBancaireFacade compteBancaireFacade;
    
    private List<CompteBancaire> allCompteBancaire = new ArrayList<>();

    public CompteBancaireMBean() {
        
    }
    
    public List<CompteBancaire> getAllCompteBancaire(){
        System.out.println("getAllCompteBancaire Called!");
        if(this.allCompteBancaire.isEmpty()){
            System.out.println("getAllCompteBancaire CACHED!");
            List<CompteBancaire> cb = compteBancaireFacade.getAllCompteBancaire();
            allCompteBancaire = cb;
        }
        return allCompteBancaire;
    }
    
    public void resetCache(){
        this.allCompteBancaire = new ArrayList<>();
    }
    
    public void creerComptesTest(){
        bootstrap.init();
        resetCache();
    }
}
