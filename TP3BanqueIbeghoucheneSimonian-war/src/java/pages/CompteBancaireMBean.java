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
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Named;
import session.CompteManager;

@Named(value = "compteBancaireMBean")
@Stateless
@LocalBean
public class CompteBancaireMBean implements Serializable {

    @EJB
    private CompteManager compteManager;

    
    
    public CompteBancaireMBean(){
        
    }
    
    public List<CompteBancaire> getAllCompteBancaire(){
        return compteManager.getAllCompteBancaire();
    }
    
}
