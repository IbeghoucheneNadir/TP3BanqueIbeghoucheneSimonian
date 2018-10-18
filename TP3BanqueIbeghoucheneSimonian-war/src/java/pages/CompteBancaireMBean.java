/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import entities.CompteBancaire;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.CompteBancaireFacade;
import session.CompteManager;

/**
 *
 * @author katzenmaul
 */
@Named(value = "compteBancaireMBean")
@ViewScoped
public class CompteBancaireMBean implements Serializable {

    @EJB
    private CompteBancaireFacade compteBancaireFacade;
    
    private List<CompteBancaire> allCompteBancaire;
    /**
     * Creates a new instance of CompteBancaireMBean
     */
    public CompteBancaireMBean() {
    }
    
    public List<CompteBancaire> getAllCompteBancaire(){
        List<CompteBancaire> cb = compteBancaireFacade.getAllCompteBancaire();
        allCompteBancaire = cb;
        return allCompteBancaire;
    }
    
}
