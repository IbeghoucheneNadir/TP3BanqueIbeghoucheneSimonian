package pages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.CompteBancaire;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.CompteBancaireFacade;

/**
 *
 * @author mbdse
 */
@Named(value = "compteBancaireDetailsMBean")
@ViewScoped
public class CompteBancaireDetailsMBean implements Serializable{

    /**
     * Creates a new instance of CompteBancaireDetailsMBean
     */
     private int idCompteBancaire;  
  private CompteBancaire compteBancaire;  
  
  @EJB  
  private CompteBancaireFacade compteBancaireFacade;  
  
  public int getIdCustomer() {  
    return idCompteBancaire;  
  }  
  
  public void setIdCustomer(int idCompteBancaire) {  
    this.idCompteBancaire = idCompteBancaire;  
  }  
  
  /** 
   * Renvoie les détails du client courant (celui dans l'attribut customer de 
   * cette classe), qu'on appelle une propriété (property) 
   */  
  public CompteBancaire getDetails() {  
    return compteBancaire;  
  }  
  
  /** 
   * Action handler - met à jour la base de données en fonction du client passé 
   * en paramètres, et renvoie vers la page qui affiche la liste des clients. 
   */  
  public String update() {  
    System.out.println("###UPDATE###");  
    compteBancaire = compteBancaireFacade.update(compteBancaire);  
    return "CustomerList";  
  }  
  
  /** 
   * Action handler - renvoie vers la page qui affiche la liste des clients 
   */  
  public String list() {  
    System.out.println("###LIST###");  
    return "CustomerList";  
  }  
  
  public void loadCustomer() {  
    this.compteBancaire = compteBancaireFacade.getCompteBancaire(idCompteBancaire);  
  }  
    
}
